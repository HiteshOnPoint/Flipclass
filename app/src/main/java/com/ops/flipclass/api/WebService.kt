package com.saint.saintfood.api


import com.ops.flipclass.models.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface WebService {
    /*
    * login
    * */
    @POST("services/studentLogin.php")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun login(
        @Field("firstName") firstName: String?,
        @Field("lastName") lastName: String?,
        @Field("birthDate") birthDate: String?,
        @Field("googleAuthKey") googleAuthKey: String?,
        @Field("email") email: String?,
    ): Response<LoginResponse>

    /*@GET("api/banner")
    suspend fun getBanner(
        @Header("Authorization") token: String
    ): Response<BannerDetailsResponse>

    *//*
    * notifyMeProduct
    * *//*
    @POST("api/notify_me")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun notifyMeProduct(
        @Header("Authorization") token: String?,
        @Field("product_id") userPhone: String?,
        @Field("type") deviceId: String?
    ): Response<NotifyMeResponse>


    *//*
   * forget password
   * *//*
    @POST("api/forgetPassword")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun forgetPassword(
        @Field("userPhone") userPhone: String?,
        @Field("deviceId") deviceId: String?
    ): Response<ForgetResponse>

    *//*
    * register
    * *//*
    @POST("api/register")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun register(
        @Header("DeviceType") deviceType: String?,
        @Field("userPhone") userPhone: String?,
        @Field("is_guest")  isGuest: String?,
        @Field("deviceId") deviceId: String?,
        @Field("fcmToken") fcmToken: String?
    ): Response<RegisterResponse>

    *//*
    * register with city & phone
    * *//*
    @POST("api/register")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun RegisterWithCityPhone(
        @Header("DeviceType") deviceType: String?,
        @Field("city") city: String?,
        @Field("userPhone") userPhone: String?,
        @Field("is_guest")  isGuest: String?,
        @Field("deviceId") deviceId: String?,
        @Field("fcmToken") fcmToken: String?
    ): Response<RegisterResponse>

    *//*
    * register with City
    * *//*
    @POST("api/register")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun RegisterWithCity(
        @Header("DeviceType") deviceType: String?,
        @Field("city") city: String?,
        @Field("is_guest")  isGuest: String?,
        @Field("deviceId") deviceId: String?,
        @Field("fcmToken") fcmToken: String?
    ): Response<RegisterResponse>

    *//*
    * verify phone number
    * *//*
    @POST("api/verifyPhone")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun verifyPhone(
        @Field("userPhone") userPhone: String?,
        @Field("otp") otp: String?,
        @Field("deviceId") deviceId: String?,
        @Field("fcmToken") fcmToken: String?
    ): Response<VerifyPhoneResponse>


    @POST("api/profileAddress")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun updateProfileInfo(
        @Header("Authorization") token: String,
        @Field("userFirstName") userFirstName: String?,
        @Field("userLastName") userLastName: String?,
        @Field("userEmail") userEmail: String?,
        @Field("password") fcmToken: String?,
        @Field("userImage") userImage: String?,
        @Field("fullAddress") fullAddress: String?,
        @Field("houseNo") houseNo: String?,
        @Field("city") city: Int?,
        @Field("landmark") landmark: String?,
        @Field("pin") pin: String?,
        @Field("addType") addType: String?,
        @Field("latitude") latitude: String?,
        @Field("longitude") longitude: String?
    ): Response<UserInfoResponse>


    @GET("api/getFeaturedCat")
    suspend fun getFeaturedCategory(
        @Header("Authorization") token: String
    ): Response<FeaturedResponse>

    @GET("api/getCat")
    suspend fun getCategory(
        @Header("Authorization") token: String
    ): Response<CategoryResponse>

    *//*
    * category product
    * *//*
    @POST("api/cat_product")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun getCategoryProduct(
        @Header("Authorization") token: String?,
        @Field("cat_id") catId: String?
    ): Response<CategoryProductResponse>

    *//*
    * add to cart
    * *//*
    @POST("api/addToCart")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun addToCart(
        @Header("Authorization") token: String?,
        @Field("categoryId") categoryId: Int?,
        @Field("productId") productId: Int?,
        @Field("varientId") varientId: Int?,
        @Field("quantity") quantity: Int?
    ): Response<AddCartResponse>

    *//*
    * discard from cart
    * *//*
    @POST("api/discartFromCart")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun discardFromCart(
        @Header("Authorization") token: String?,
        @Field("categoryId") categoryId: Int?,
        @Field("productId") productId: Int?,
        @Field("varientId") varientId: Int?,
        @Field("quantity") quantity: Int?
    ): Response<AddCartResponse>

    *//*
    * get to cart
    * *//*
    @POST("api/getUserCart")
    suspend fun getCart(
        @Header("Authorization") token: String?
    ): Response<MyCartResponse>

    *//*
   * remove cart
   * *//*
    @POST("api/removeFromCart")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun removeFromCart(
        @Header("Authorization") token: String?,
        @Field("cartId") cartId: String?
    ): Response<MyCartResponse>

    *//*
  * search product
  * *//*
    @POST("api/search")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun searchProduct(
        @Header("Authorization") token: String?,
        @Field("keyword") keyword: String?
    ): Response<SearchProductResponse>

    *//*
     * recent search product
     * *//*
    @GET("api/getRecentSearch")
    suspend fun getRecentSearch(
        @Header("Authorization") token: String?
    ): Response<RecentSearchListResponse>

    *//*
     * add recent search product
     * *//*
    @POST("api/addRecentSearch")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun addRecentSearch(
        @Header("Authorization") token: String?,
        @Field("productId") productId: Int?
    ): Response<AddRecentSearchResponse>

    *//*
    * delete recent search
    * *//*
    @GET("api/deleteRecentSearch")
    suspend fun deleteRecentSearch(
        @Header("Authorization") token: String?
    ): Response<DeleteSearchResponse>

    *//*
    * swap discarded list
    * *//*
    @POST("api/getUserCategoryDiscartedList")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun swapDiscardedList(
        @Header("Authorization") token: String?,
        @Field("categoryId") categoryId: String?
    ): Response<ProductSwapDiscardedResponse>

    *//*
    * Get app info
    * *//*
    @GET("api/appInfo")
    suspend fun getAppInfo(
        @Header("Authorization") token: String?,
        @Header("DeviceType") deviceType: String?,
        @Header("add_tracking_id") adTrackingId: String?
    ): Response<AppInfoResponse>

    *//*
    * Get Profile details
    * *//*
    @GET("api/getProfile")
    suspend fun getProfile(
        @Header("Authorization") token: String?
    ): Response<GetProfileResponse>


    *//*
    * Get Order ongoing
    * *//*
    @POST("api/ongoingOrders")
    suspend fun getOrderOngoing(
        @Header("Authorization") token: String?
    ): Response<OrderOngoingResponse>

    *//*
   * Get Order completed
   * *//*
    @POST("api/completedOrders")
    suspend fun getOrderCompleted(
        @Header("Authorization") token: String?
    ): Response<OrderHistoryResponse>

    *//*
     * Checkout order
     * *//*
    @POST("api/checkOut")
    suspend fun checkoutOrder(
        @Header("Authorization") token: String?
    ): Response<CheckoutResponse>

    *//*
    * coupon list
    * *//*
    @POST("api/couponList")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun couponList(
        @Header("Authorization") token: String?,
        @Field("trasactionId") trasactionId: String?
    ): Response<CouponListResponse>

    *//*
    * apply coupon
    * *//*
    @POST("api/applyCoupon")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun applyCoupon(
        @Header("Authorization") token: String?,
        @Field("trasactionId") trasactionId: String?,
        @Field("coupon_code") couponCode: String?
    ): Response<CheckoutResponse>


    *//*
   * remove coupon
   * *//*
    @POST("api/removeCoupon")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun removeCoupon(
        @Header("Authorization") token: String?,
        @Field("trasactionId") trasactionId: String?
    ): Response<RemoveCouponResponse>

    *//*
     * place order
     * *//*
    @POST("api/placeOrder")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun placeOrder(
        @Header("Authorization") token: String?,
        @Field("deliveryDate") deliveryDate: String?,
        @Field("timeSlot") timeSlot: String?,
        @Field("trasactionId") trasactionId: String?,
        @Field("addressId") addressId: String?
    ): Response<PlaceOrderResponse>

    *//*
    * time slot
    * *//*
    @POST("api/timeSlot")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun getTimeSlot(
        @Header("Authorization") token: String?,
        @Field("selected_date") deliveryDate: String?
    ): Response<TimeSlotResponse>

    *//*
    *cancel order
    * *//*
    @POST("api/cancelOrder")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun cancelOrder(
        @Header("Authorization") token: String?,
        @Field("transactionId") transactionId: String?,
        @Field("reason") reason: String?
    ): Response<CancelOrderResponse>

    *//*
    *Get address
    * *//*
    @POST("api/getAddress")
    suspend fun getAddress(
        @Header("Authorization") token: String?
    ): Response<GetAddressResponse>

    *//*
   *logout
   * *//*
    @POST("api/logout")
    suspend fun userLogout(
        @Header("Authorization") token: String?
    ): Response<LogoutResponse>


    *//*
    * update profile
    * *//*
    @POST("api/updateProfile")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun updateUserProfile(
        @Header("Authorization") token: String?,
        @Field("userFirstName") userFirstName: String?,
        @Field("userLastName") userLastName: String?,
        @Field("userEmail") userEmail: String?
    ): Response<UpdateUserResponse>


    *//*
    * update password
    * *//*
    @POST("api/updateProfile")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun updateUserPassword(
        @Header("Authorization") token: String?,
        @Field("password") password: String?,
        @Field("oldPassword") oldPassword: String?
    ): Response<UpdateUserResponse>

    *//*
    * update phone no
    * *//*
    @POST("api/checkPhone")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun updateUserPhoneNo(
        @Header("Authorization") token: String?,
        @Field("userPhone") userPhone: String?
    ): Response<UpdatePhoneResponse>

    *//*
    * update verify phone
    * *//*
    @POST("api/verifyUpdatePhone")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun updateUserVerifyPhoneNo(
        @Header("Authorization") token: String?,
        @Field("userPhone") userPhone: String?,
        @Field("otp") otp: String?
    ): Response<UpdateVerifyPhoneResponse>

    *//*
    * update phone no
    * *//*
    @POST("api/notificationlist")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun notificationList(
        @Header("Authorization") token: String?,
        @Field("mark_read") id: String?
    ): Response<NotificationResponse>

    @POST("api/mark_all_as_read")
    suspend fun markAllAsReadNotification(
        @Header("Authorization") token: String?
    ): Response<NotificationResponse>

    @POST("api/mark_as_read")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun markSingleAsReadNotification(
        @Header("Authorization") token: String?,
        @Field("noti_id") id: String?
    ): Response<NotificationResponse>

    *//*
    * update Dietary Pref
    * *//*
    @POST("api/updateProfile")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun updateUserDietaryPref(
        @Header("Authorization") token: String?,
        @Field("dietType") dietType: String?
    ): Response<UpdateUserResponse>

    *//*
    * update Dietary Pref
    * *//*
    @POST("api/updateProfile")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun updateUserFamilyStrength(
        @Header("Authorization") token: String?,
        @Field("familyMembers") familyMembers: Int?
    ): Response<UpdateUserResponse>

    *//*
    * add address
    * *//*
    @POST("api/addAddress")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun addAddress(
        @Header("Authorization") token: String?,
        @Field("fullAddress") fullAddress: String?,
        @Field("houseNo") houseNo: String?,
        @Field("landmark") landmark: String?,
        @Field("pin") pin: String?,
        @Field("city") city: String?,
        @Field("addType") addType: String?,
        @Field("latitude") latitude: String?,
        @Field("longitude") longitude: String?,
        @Field("selectStatus") selectStatus: String?,
        @Field("receiverName") receiverName: String?,
        @Field("receiverPhone") receiverPhone: String?
    ): Response<AddAddressResponse>

    *//*
   * edit address
   * *//*
    @POST("api/editAddress")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun editAddress(
        @Header("Authorization") token: String?,
        @Field("addressId") addressId: String?,
        @Field("fullAddress") fullAddress: String?,
        @Field("houseNo") houseNo: String?,
        @Field("landmark") landmark: String?,
        @Field("pin") pin: String?,
        @Field("city") city: String?,
        @Field("addType") addType: String?,
        @Field("latitude") latitude: String?,
        @Field("longitude") longitude: String?,
        @Field("selectStatus") selectStatus: String?,
        @Field("receiverName") receiverName: String?,
        @Field("receiverPhone") receiverPhone: String?
    ): Response<EditAddressResponse>

    *//*
  * set default address
  * *//*
    @POST("api/setDefault")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun setDefaultAddress(
        @Header("Authorization") token: String?,
        @Field("addressId") addressId: Int?
    ): Response<EditAddressResponse>

    *//*
  * delete address
  * *//*
    @POST("api/deleteAddress")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun deleteAddress(
        @Header("Authorization") token: String?,
        @Field("addressId") addressId: Int?
    ): Response<DeleteAddressResponse>

    *//*
 * order details
 * *//*
    @POST("api/getOrderByID")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun getOrderDetails(
        @Header("Authorization") token: String?,
        @Field("transactionId") transactionId: String?
    ): Response<OrderDetailResponse>

    *//*
 * paymentComplete
 * *//*
    @POST("api/paymentComplete")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun paymentComplete(
        @Header("Authorization") token: String?,
        @Field("transactionId") transactionId: String?,
        @Field("app_payment_id") app_payment_id: String?,
        @Field("paymentMethod") paymentMethod: String?,
        @Field("paymentStatus") paymentStatus: String?
    ): Response<PaymentCompleteResponse>

    *//*
    * place autocomplete search
    * *//*
    @GET("https://maps.googleapis.com/maps/api/place/autocomplete/json")
    suspend fun placeSearch(
        @Query("input") query: String?,
        @Query("key") key: String?,
        @Query("sessiontoken") sessionToken: String?,
        @Query("region") region: String?
    ): Response<PlaceSearchResponse>

    *//*
    * get address from lattitude and longitude
    * *//*
    @GET("https://maps.googleapis.com/maps/api/geocode/json")
    suspend fun getAddressFromApi(
        @Query("latlng") latlng: String?,
        @Query("key") key: String?,
        @Query("sensor") sensor: Boolean?
    ): Response<AddressDetailsResponse>

    *//*
    * re order
    * *//*
    @POST("api/reorder")
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    suspend fun reOrder(
        @Header("Authorization") token: String?,
        @Field("transactionID") trasactionId: String?
    ): Response<ReOrderResponse>

    *//*
    * Get app info
    * *//*
    @GET("api/getCityList")
    suspend fun getCityList(): Response<GetCityResponse>*/

    companion object {
        //const val BASE_URL = "https://webapi.saintfarms.com/"     //Production
        const val BASE_URL = "https://educasters.onpointsoft.com/"          //Staging
    }
}