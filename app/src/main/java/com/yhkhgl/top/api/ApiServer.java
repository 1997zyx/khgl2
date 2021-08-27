package com.yhkhgl.top.api;



import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.TextBean;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * File descripition:
 *
 * @author lp
 * @date 2018/6/19
 */

public interface ApiServer {

    /**
     * 大文件官方建议用 @Streaming 来进行注解，不然会出现IO异常，小文件可以忽略不注入
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);


    /**
     * 客户标签的筛选项
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("v1/notice/notice/notice_list")
    Observable<BaseModel<Object>> getTableList(@FieldMap HashMap<String, String> params);
    /**
     * 当前城市限行
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/restrictions/")
    Observable<BaseModel<Object>> getRestrictions(@FieldMap HashMap<String, String> params);
    /**
     * 当前城市限行
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("/api/public/banners")
    Observable<BaseModel<Object>> getCeShi(@FieldMap HashMap<String, String> params);


    /**
     * 第一种写法
     *
     * @param requestType
     * @return
     */
    @POST("api/Activity/get_activities?")
    Observable<BaseModel<List<MainBean>>> getMain(@Query("time") String requestType);

    /**
     * 第二种写法
     *
     * @param requestType
     * @return
     */
    @FormUrlEncoded
    @POST("api/Activity/get_activities?")
    Observable<BaseModel<List<MainBean>>> getMain2(@Field("time") String requestType);

    /**
     * 第三种写法
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api/Activity/get_activities?")
    Observable<BaseModel<HashMap<String, String>>> getMain3(@FieldMap HashMap<String, String> params);

    /**
     * text
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("index?")
    Observable<BaseModel<TextBean>> getText(@FieldMap HashMap<String, String> params);

    /**
     * 演示 单图上传
     *
     * @param parts
     * @return
     */
    @Multipart
    @POST("api/Company/register")
    Observable<BaseModel<Object>> upLoadImg(@Part MultipartBody.Part parts);

    /**
     * 演示 多图上传
     *
     * @param parts
     * @return
     */
    @Multipart
    @POST("api/user_info/update_headimg")
    Observable<BaseModel<Object>> upHeadImg(@Part List<MultipartBody.Part> parts);

    /**
     * 演示 图片字段一起上传
     *
     * @param parts
     * @return
     */
    @Multipart
    @POST("api/Express/add")
    Observable<BaseModel<Object>> expressAdd(@PartMap Map<String, RequestBody> map,
                                             @Part List<MultipartBody.Part> parts);

    /**
     * 上传图片/视频
     * <p>
     * //     * @param token 用户登录的token
     * //     * @param file  文件流
     *
     * @return
     */
    @Multipart
    @POST("/wxapp/public/upload")
    Observable<BaseModel<Object>> getUpload(@PartMap Map<String, RequestBody> map,
                                            @Part MultipartBody.Part parts
    );


//    /**
//     * 演示特殊结构写法
//     *
//     * @param requestType
//     * @return
//     */
//    @POST("api/Activity/get_activities?")
//    Observable<BaseModel<List<Bean1>, Bean2, List<Bean3>>> getMain2Demo(@Query("time") String requestType);
//
//    /**
//     * 演示特殊结构写法
//     *
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api/Activity/get_activities?")
//    Observable<BaseModel<List<Bean1>, Bean2, List<Bean3>>> getMain3Demo(@FieldMap HashMap<String, String> params);


//    @Multipart
//    @POST("v1/common/upload")
//    Observable<BaseModel<Object>> Uploadimg(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part parts
//    );
    /**
     * 演示 单图上传,加字段
     *
     * @param
     * @return
     */
    @Multipart
    @POST("v1/common/upload")
    Observable<BaseModel<Object>> Uploadimg(@PartMap Map<String, RequestBody> map,@Part MultipartBody.Part part);


    @Multipart
    @POST("v1/common/upload")
    Observable<BaseModel<Object>> uploadmore( @Part List<MultipartBody.Part> parts);

    /**
     * 获取首页轮播图
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/home/home/banner_list")
    Observable<BaseModel<Object>> getBanner(@FieldMap HashMap<String, String> params);
    /**
     * 首页合作服务商
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/service_list")
    Observable<BaseModel<Object>> service_list(@FieldMap HashMap<String, String> params);

    /**
     * 产品列表
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/product_list")
    Observable<BaseModel<Object>> product_list(@FieldMap HashMap<String, String> params);

    /**
     * 用户提交资质
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/add_information")
    Observable<BaseModel<Object>> add_information(@FieldMap HashMap<String, String> params);

    /**
     * 用户提交资质匹配页面
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/match_result")
    Observable<BaseModel<Object>> match_result(@FieldMap HashMap<String, String> params);

    /**
     * 方案定制
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/add_user_customization")
    Observable<BaseModel<Object>> add_user_customization(@FieldMap HashMap<String, String> params);

    /**
     * 登录
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/login")
    Observable<BaseModel<Object>>login(@FieldMap HashMap<String, String> params);

    /**
     * 发送验证码
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/message/send_sms")
    Observable<BaseModel<Object>>send_sms(@FieldMap HashMap<String, String> params);

    /**
     * 注册账号
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/reg")
    Observable<BaseModel<Object>>reg(@FieldMap HashMap<String, String> params);

    /**
     * 重置密码
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/retrieve_password")
    Observable<BaseModel<Object>>retrieve_password(@FieldMap HashMap<String, String> params);

    /**
     * 产品详情
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/product_detail")
    Observable<BaseModel<Object>>product_detail(@FieldMap HashMap<String, String> params);


    /**
     * 产品详情联系人信息
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/show_mobile")
    Observable<BaseModel<Object>>show_mobile(@FieldMap HashMap<String, String> params);

    /**
     * 产品详情打电话
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/contact_number")
    Observable<BaseModel<Object>>contact_number(@FieldMap HashMap<String, String> params);

    /**
     * 产品详情收藏接口
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/product_collect")
    Observable<BaseModel<Object>>product_collect(@FieldMap HashMap<String, String> params);

    /**
     * 服务商详情
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/service_detail")
    Observable<BaseModel<Object>>service_detail(@FieldMap HashMap<String, String> params);

    /**
     * 服务商详情收藏
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/service_collect")
    Observable<BaseModel<Object>>service_collect(@FieldMap HashMap<String, String> params);

    /**
     * 个人中心
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/info")
    Observable<BaseModel<Object>>info(@FieldMap HashMap<String, String> params);

    /**
     * 提交建议
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/feedback")
    Observable<BaseModel<Object>>feedback(@FieldMap HashMap<String, String> params);

    /**
     * 修改
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/edit_info")
    Observable<BaseModel<Object>>edit_info(@FieldMap HashMap<String, String> params);


    /**
     * 服务商加入
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/add_settlement_service")
    Observable<BaseModel<Object>>add_settlement_service(@FieldMap HashMap<String, String> params);




    /**
     * 服务商加入
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/add_bank_outlets ")
    Observable<BaseModel<Object>>add_bank_outlets(@FieldMap HashMap<String, String> params);

    /**
     * 消息模块
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/notice/notice/notice_center")
    Observable<BaseModel<Object>>notice_center(@FieldMap HashMap<String, String> params);
    /**
     * 消息模块详情
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/notice/notice/center_list")
    Observable<BaseModel<Object>>center_list(@FieldMap HashMap<String, String> params);

    /**
     * 足迹
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/user_footmark")
    Observable<BaseModel<Object>>user_footmark(@FieldMap HashMap<String, String> params);

    /**
     * 产品收藏列表
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/product_collect_list")
    Observable<BaseModel<Object>>product_collect_list(@FieldMap HashMap<String, String> params);

    /**
     * 服务商收藏列表
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/service_collect_list")
    Observable<BaseModel<Object>>service_collect_list(@FieldMap HashMap<String, String> params);

    /**
     * 支付宝订单
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/order/do_order")
    Observable<BaseModel<Object>>do_order(@FieldMap HashMap<String, String> params);

    /**
     * 收藏产品删除
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/product_collect_del")
    Observable<BaseModel<Object>>product_collect_del(@FieldMap HashMap<String, String> params);

    /**
     * 收藏服务商删除
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/service_collect_del")
    Observable<BaseModel<Object>>service_collect_del(@FieldMap HashMap<String, String> params);



    /**
     * 更新
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/common/android_apk")
    Observable<BaseModel<Object>>android_apk(@FieldMap HashMap<String, String> params);

    /**
     * 个人中心获取banner
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/banner_list")
    Observable<BaseModel<Object>>banner_list_info(@FieldMap HashMap<String, String> params);


    /**团队
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/my_team")
    Observable<BaseModel<Object>>my_team(@FieldMap HashMap<String, String> params);

    /**
     *积分
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/point_record")
    Observable<BaseModel<Object>>point_record(@FieldMap HashMap<String, String> params);

    /**
     *积分兑换
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(" v1/goods/goods/list")
    Observable<BaseModel<Object>>list(@FieldMap HashMap<String, String> params);

    /**
     *积分兑换记录
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/exchange_record")
    Observable<BaseModel<Object>>exchange_record(@FieldMap HashMap<String, String> params);

    /**
     *商品详情
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/goods/goods/goods_detail")
    Observable<BaseModel<Object>>goods_detail(@FieldMap HashMap<String, String> params);

    /**
     *商品立即兑换提交地址
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/add_rec_info")
    Observable<BaseModel<Object>>add_rec_info(@FieldMap HashMap<String, String> params);

    /**
     *商品立即兑换
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/exchange_goods")
    Observable<BaseModel<Object>>exchange_goods(@FieldMap HashMap<String, String> params);

    /**
     *发现推荐
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/article/article/article_list")
    Observable<BaseModel<Object>>article_list(@FieldMap HashMap<String, String> params);

    /**
     *发现推荐点赞收藏
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/article/article/article_like_collect")
    Observable<BaseModel<Object>>article_like_collect(@FieldMap HashMap<String, String> params);

    /**
     *热点
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/news/news/news_list")
    Observable<BaseModel<Object>>news_list(@FieldMap HashMap<String, String> params);

    /**
     *热点详情
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/news/news/news_detail")
    Observable<BaseModel<Object>>news_detail(@FieldMap HashMap<String, String> params);

    /**
     *关注
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/follow_user")
    Observable<BaseModel<Object>>follow_user(@FieldMap HashMap<String, String> params);

    /**
     *举报
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/article/article/article_report")
    Observable<BaseModel<Object>>article_report(@FieldMap HashMap<String, String> params);

    /**
     *详情
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/article/article/article_detail")
    Observable<BaseModel<Object>>article_detail(@FieldMap HashMap<String, String> params);

    /**
     *详情评论列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/article/article/article_evaluation_list")
    Observable<BaseModel<Object>>article_evaluation_list(@FieldMap HashMap<String, String> params);

    /**
     *发送评论
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/article/article/add_article_evaluation")
    Observable<BaseModel<Object>>add_article_evaluation(@FieldMap HashMap<String, String> params);

    /**
     *点赞
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/article/article/article_evaluation_praise_tread")
    Observable<BaseModel<Object>>article_evaluation_praise_tread(@FieldMap HashMap<String, String> params);


    /**
     *提交发现
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/article/article/add_article")
    Observable<BaseModel<Object>>add_article(@FieldMap HashMap<String, String> params);

    /**
     *个人主页
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/other_user_info")
    Observable<BaseModel<Object>>other_user_info(@FieldMap HashMap<String, String> params);

    /**
     *个人主页列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/follow_user_article")
    Observable<BaseModel<Object>>follow_user_article(@FieldMap HashMap<String, String> params);

    /**
     *关注列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST(" v1/user/user/follow_list")
    Observable<BaseModel<Object>>follow_list(@FieldMap HashMap<String, String> params);


    /**
     *粉丝列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/fans_list")
    Observable<BaseModel<Object>>fans_list(@FieldMap HashMap<String, String> params);


/**
 *删除评论
 * @param
 * @return
 */
    @FormUrlEncoded
    @POST("v1/user/user/del_article")
    Observable<BaseModel<Object>>del_article(@FieldMap HashMap<String, String> params);

    /**
     *个人中心认证
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/real_name")
    Observable<BaseModel<Object>>real_name(@FieldMap HashMap<String, String> params);

    /**
     *搜索产品
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/search_data")
    Observable<BaseModel<Object>>search_data(@FieldMap HashMap<String, String> params);

    /**
     *更多房源
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/product_tj_list")
    Observable<BaseModel<Object>>product_tj_list(@FieldMap HashMap<String, String> params);

    /**
     *添加信用卡
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/note/do_credit_card")
    Observable<BaseModel<Object>>do_credit_card(@FieldMap HashMap<String, String> params);

    /**
     *信用卡列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/note/credit_list")
    Observable<BaseModel<Object>>credit_list(@FieldMap HashMap<String, String> params);

    /**
     *信用卡详情
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/note/credit_info")
    Observable<BaseModel<Object>>credit_info(@FieldMap HashMap<String, String> params);

    /**
     *信用卡删除
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/note/credit_del")
    Observable<BaseModel<Object>>credit_del(@FieldMap HashMap<String, String> params);

    /**
     *添加记账
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/note/do_bill")
    Observable<BaseModel<Object>>do_bill(@FieldMap HashMap<String, String> params);
    /**
     *记账记录
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/note/user_bill_list")
    Observable<BaseModel<Object>>user_bill_list(@FieldMap HashMap<String, String> params);

    /**
     *记账统计
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/note/user_bill_statistics")
    Observable<BaseModel<Object>>bill_statistics(@FieldMap HashMap<String, String> params);

    /**
     *获取城市
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/first_list")
    Observable<BaseModel<Object>>first_list(@FieldMap HashMap<String, String> params);

    /**
     *获取区
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/user/second_list")
    Observable<BaseModel<Object>>second_list(@FieldMap HashMap<String, String> params);


    /**
     *征信网点列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/bank/outlets/outlets_list_new")
    Observable<BaseModel<Object>>outlets_list_new(@FieldMap HashMap<String, String> params);


    /**
     *添加对比
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/add_more_cart")
    Observable<BaseModel<Object>>add_more_cart(@FieldMap HashMap<String, String> params);
    /**
     *对比列表
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/cart_product_list")
    Observable<BaseModel<Object>>cart_product_list(@FieldMap HashMap<String, String> params);

    /**
     *对比详情
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/product/product/product_compare")
    Observable<BaseModel<Object>>product_compare(@FieldMap HashMap<String, String> params);


    /**
     * 客户管理筛选
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/condition_content")
    Observable<BaseModel<Object>> condition_content(@FieldMap HashMap<String, String> params);

    /**
     * 客户列表
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/customer_list")
    Observable<BaseModel<Object>> customer_list(@FieldMap HashMap<String, String> params);

    /**
     * 客户添加
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/customer_edit")
    Observable<BaseModel<Object>> customer_edit(@FieldMap HashMap<String, String> params);

    /**
     * 客户详情
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/customer_detail")
    Observable<BaseModel<Object>> customer_detail(@FieldMap HashMap<String, String> params);
    /**
     * 添加跟进记录
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/edit_follow")
    Observable<BaseModel<Object>> edit_follow(@FieldMap HashMap<String, String> params);


    /**
     * 客户跟进记录列表
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/cus_follow_list")
    Observable<BaseModel<Object>> cus_follow_list(@FieldMap HashMap<String, String> params);


    /**
     * 客户跟进记录删除
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/follow_del")
    Observable<BaseModel<Object>> follow_del(@FieldMap HashMap<String, String> params);

    /**
     * 获取话术
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/speech_technique")
    Observable<BaseModel<Object>> speech_technique(@FieldMap HashMap<String, String> params);

    /**
     * 提交话术
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/speech_technique_update")
    Observable<BaseModel<Object>> speech_technique_update(@FieldMap HashMap<String, String> params);

    /**
     * 客户搜索
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/user_customization_search")
    Observable<BaseModel<Object>> user_customization_search(@FieldMap HashMap<String, String> params);

    /**
     * 薪资列表
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/employee_compensation_list")
    Observable<BaseModel<Object>> employee_compensation_list(@FieldMap HashMap<String, String> params);

    /**
     * 佣金列表
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/user/customer/user_customization_history")
    Observable<BaseModel<Object>>user_customization_history(@FieldMap HashMap<String, String> params);

    /**
     * 佣金管理列表
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/commission_manage")
    Observable<BaseModel<Object>>commission_manage(@FieldMap HashMap<String, String> params);

    /**
     * 薪资详情
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/employee_compensation")
    Observable<BaseModel<Object>>employee_compensation(@FieldMap HashMap<String, String> params);

    /**
     * 获取日期列表
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/service_date_list")
    Observable<BaseModel<Object>> service_date_list(@FieldMap HashMap<String, String> params);

    /**
     * 佣金管理日期
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/commission_date_list")
    Observable<BaseModel<Object>>commission_date_list(@FieldMap HashMap<String, String> params);

    /**
     * 薪资明细日期
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("v1/service/service/staff_date_list")
    Observable<BaseModel<Object>> staff_date_list(@FieldMap HashMap<String, String> params);
}
