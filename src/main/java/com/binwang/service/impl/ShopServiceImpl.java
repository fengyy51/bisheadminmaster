package com.binwang.service.impl;

import com.binwang.bean.shop.ShopListModel;
import com.binwang.bean.shop.ShopParam;
import com.binwang.bean.shop.ShopResource;
import com.binwang.dao.IShopDao;
import com.binwang.exception.UserException;
import com.binwang.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by owen on 17/6/7.
 */
@Service
public class ShopServiceImpl implements ShopService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Resource
    private IShopDao shopDao;

    private final ExecutorService exec = Executors.newFixedThreadPool(10);

    private class addResource implements Callable<Boolean> {
        private ShopResource shopResource;

        public addResource(ShopResource shopResource) {
            this.shopResource = shopResource;
        }

        @Override
        public Boolean call() throws Exception {
            int res = shopDao.addResource(shopResource);
            if (res > 0)
                return true;
            else
                return false;
        }
    }

    @Override
    @Transactional
    public Boolean add(ShopParam shopParam) {
        int res = shopDao.addShop(shopParam);
        if (res > 0) {
            //插入主表成功
            List<String> shopImgs = shopParam.getShopImgs();
            if (shopImgs.size() > 0) {
                long shopId = shopParam.getId();
                List<addResource> tasks = new ArrayList<>();
                for (String imgUrl : shopImgs) {
                    ShopResource s = new ShopResource(shopId, imgUrl);
                    tasks.add(new addResource(s));
                }
                try {
                    List<Future<Boolean>> results = exec.invokeAll(tasks);
                    for (Future<Boolean> f : results) {
                        if (!f.get())
                            throw new UserException("附表记录添加失败");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().isInterrupted();
                    LOGGER.error("添加店铺信息附表插入数据库被中断");
                    throw new UserException("附表记录添加失败");
                } catch (ExecutionException e) {
                    LOGGER.error("添加店铺信息附表插入数据库执行异常");
                    throw new UserException("附表记录添加失败");
                }
            }
            return true;
        } else {
            LOGGER.error("主表记录添加失败");
            throw new UserException("主表记录添加失败");
        }
    }

    @Override
    public List<ShopListModel> list(int curPage, int pageSum, String name, int area, int floor) {
        try {
            List<ShopListModel> res = shopDao.listShop(name, area, floor, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            LOGGER.error("获取店铺列表失败");
            throw new UserException("获取店铺列表失败");
        }
    }

    @Override
    public int listSum(String name, int area, int floor) {
        try {
            int sum = shopDao.listShopSum(name, area, floor);
            return sum;
        } catch (Exception e) {
            LOGGER.error("统计店铺列表数量失败");
            throw new UserException("统计店铺列表数量失败");
        }
    }

    @Override
    public ShopParam get(long id) {
        if (id <= 0)
            throw new UserException("店铺id不符合要求");
        try {
            ShopParam s = shopDao.findById(id);
            List<String> imgList = shopDao.findResourceByShopId(id);
            s.setShopImgs(imgList);
            return s;
        } catch (Exception e) {
            LOGGER.error("店铺查询失败，id:" + id);
            throw new UserException("店铺详情查询失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean edit(ShopParam shopParam) {
        int mainRes = shopDao.edit(shopParam);
        long shopId = shopParam.getId();
        if (mainRes > 0) {
            Boolean shopImgsChange = shopParam.getShopImgsChange();
            if (shopImgsChange) {
                //附属表有变化
                shopDao.deleteResource(shopId);
                List<String> imgs = shopParam.getShopImgs();
                List<addResource> tasks = new ArrayList<>();
                for (String s : imgs) {
                    ShopResource ss = new ShopResource(shopId, s);
                    tasks.add(new addResource(ss));
                }
                try {
                    List<Future<Boolean>> futures = exec.invokeAll(tasks);
                    for (Future<Boolean> f : futures) {
                        if (!f.get())
                            throw new UserException("店铺附属表添加失败");
                    }
                    return true;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.error("店铺附属表删除失败，因为中断，id:" + shopId);
                    throw new UserException("店铺附属表添加失败,因为线程中断");
                } catch (ExecutionException e) {
                    LOGGER.error("店铺附属表删除失败，id:" + shopId);
                    throw new UserException("店铺附属表添加失败");
                }
            } else
                return true;
        } else {
            LOGGER.error("店铺主表更新失败，id:" + shopId);
            throw new UserException("店铺主表更新失败");
        }
    }

    @Override
    @Transactional
    public Boolean delete(long id) {
        try {
            int mainRes = shopDao.delete(id);
            shopDao.deleteResource(id);
            if(mainRes > 0)
                return true;
            else {
                LOGGER.error("店铺删除传入不存在id:" + id);
                throw new UserException("店铺删除传入不存在id");
            }
        } catch (Exception e) {
            LOGGER.error("店铺删除接口出错，id:" + id);
            throw new UserException("店铺删除接口出错");
        }
    }
}
