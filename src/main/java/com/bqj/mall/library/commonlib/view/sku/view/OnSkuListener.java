package com.bqj.mall.library.commonlib.view.sku.view;


import com.bqj.mall.library.commonlib.view.sku.bean.Sku;
import com.bqj.mall.library.commonlib.view.sku.bean.SkuAttribute;

/**
 * Created by wuhenzhizao on 2017/8/30.
 */
public interface OnSkuListener {
  /**
   * 属性取消选中
   *
   * @param unselectedAttribute
   */
  void onUnselected(SkuAttribute unselectedAttribute);

  /**
   * 属性选中
   *
   * @param selectAttribute
   */
  void onSelect(SkuAttribute selectAttribute);

  /**
   * sku选中
   *
   * @param sku
   */
  void onSkuSelected(Sku sku);
}
