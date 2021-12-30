package com.saint.saintfood.repository

import com.ops.flipclass.APiState
import com.ops.flipclass.models.LoginResponse
import com.saint.saintfood.api.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class ApiRepository(private val apiService: WebService) {

    /*
    * login
    * */
    fun login(
        firstName: String,
        lastName: String,
        googleAuthKey: String,
        email: String
    ): Flow<APiState<LoginResponse>> {
        return object : NetworkBoundRepository<LoginResponse>() {
            override suspend fun fetchFromRemote(): Response<LoginResponse> =
                apiService.login(firstName, lastName, "1982-04-17", googleAuthKey, email)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    /*
       * getBanner Data
       * *//*
    fun getBanner(token: String): Flow<APiState<BannerDetailsResponse>> {
        return object : NetworkBoundRepository<BannerDetailsResponse>() {
            override suspend fun fetchFromRemote(): Response<BannerDetailsResponse> =
                apiService.getBanner(token)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
   * notifyMeProduct
   * *//*
    fun notifyMeProduct(
        token: String,
        product_id: String,
        type: String
    ): Flow<APiState<NotifyMeResponse>> {
        return object : NetworkBoundRepository<NotifyMeResponse>() {
            override suspend fun fetchFromRemote(): Response<NotifyMeResponse> =
                apiService.notifyMeProduct(token,product_id, type)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
   * forget
   * *//*
    fun forgetPassword(
        userPhone: String,
        deviceId: String
    ): Flow<APiState<ForgetResponse>> {
        return object : NetworkBoundRepository<ForgetResponse>() {
            override suspend fun fetchFromRemote(): Response<ForgetResponse> =
                apiService.forgetPassword(userPhone, deviceId)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * register
    * *//*
    fun registration(
        userPhone: String,
        isGuest: String,
        deviceId: String,
        fcmToken: String
    ): Flow<APiState<RegisterResponse>> {
        return object : NetworkBoundRepository<RegisterResponse>() {
            override suspend fun fetchFromRemote(): Response<RegisterResponse> =
                apiService.register("Android",userPhone,isGuest, deviceId, fcmToken)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * register with city & phone
    * *//*
    fun registerWithCityPhone(
        city: String,
        userPhone: String,
        isGuest: String,
        deviceId: String,
        fcmToken: String
    ): Flow<APiState<RegisterResponse>> {
        return object : NetworkBoundRepository<RegisterResponse>() {
            override suspend fun fetchFromRemote(): Response<RegisterResponse> =
                apiService.RegisterWithCityPhone("Android",city ,userPhone,isGuest, deviceId, fcmToken)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * register with City
    * *//*
    fun registerWithCity(
        city: String,
        isGuest: String,
        deviceId: String,
        fcmToken: String
    ): Flow<APiState<RegisterResponse>> {
        return object : NetworkBoundRepository<RegisterResponse>() {
            override suspend fun fetchFromRemote(): Response<RegisterResponse> =
                apiService.RegisterWithCity("Android",city ,isGuest, deviceId, fcmToken)
        }.asFlow().flowOn(Dispatchers.IO)
    }


    *//*
   * verify phone number
   * *//*
    fun verifyPhone(
        userPhone: String,
        otp: String,
        deviceId: String,
        fcmToken: String
    ): Flow<APiState<VerifyPhoneResponse>> {
        return object : NetworkBoundRepository<VerifyPhoneResponse>() {
            override suspend fun fetchFromRemote(): Response<VerifyPhoneResponse> =
                apiService.verifyPhone(userPhone, otp, deviceId, fcmToken)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
  * Update User Info
  * *//*
    fun updateProfileInfo(
        token: String,
        userFirstName: String,
        userLastName: String,
        userEmail: String,
        password: String,
        userImage: String,
        fullAddress: String,
        houseNo: String,
        city: Int,
        landmark: String,
        pin: String,
        addType: String,
        latitude: String,
        longitude: String
    ): Flow<APiState<UserInfoResponse>> {
        return object : NetworkBoundRepository<UserInfoResponse>() {
            override suspend fun fetchFromRemote(): Response<UserInfoResponse> =
                apiService.updateProfileInfo(
                    token, userFirstName, userLastName, userEmail, password, userImage,
                    fullAddress, houseNo,city, landmark, pin, addType, latitude, longitude
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
        * featured Category
        * *//*
    fun getFeaturedCategory(
        token: String
    ): Flow<APiState<FeaturedResponse>> {
        return object : NetworkBoundRepository<FeaturedResponse>() {
            override suspend fun fetchFromRemote(): Response<FeaturedResponse> =
                apiService.getFeaturedCategory(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
       * Shop by Category
       * *//*
    fun getCategory(
        token: String
    ): Flow<APiState<CategoryResponse>> {
        return object : NetworkBoundRepository<CategoryResponse>() {
            override suspend fun fetchFromRemote(): Response<CategoryResponse> =
                apiService.getCategory(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
     * Category product
     * *//*
    fun getCategoryProduct(
        token: String,
        catId: String
    ): Flow<APiState<CategoryProductResponse>> {
        return object : NetworkBoundRepository<CategoryProductResponse>() {
            override suspend fun fetchFromRemote(): Response<CategoryProductResponse> =
                apiService.getCategoryProduct(token, catId)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * add to cart
    * *//*
    fun addToCart(
        token: String,
        categoryId: Int,
        productId: Int,
        varientId: Int,
        quantity: Int
    ): Flow<APiState<AddCartResponse>> {
        return object : NetworkBoundRepository<AddCartResponse>() {
            override suspend fun fetchFromRemote(): Response<AddCartResponse> =
                apiService.addToCart(
                    token, categoryId, productId,
                    varientId, quantity
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
  * discard from cart
  * *//*
    fun discardFromCart(
        token: String,
        categoryId: Int,
        productId: Int,
        varientId: Int,
        quantity: Int
    ): Flow<APiState<AddCartResponse>> {
        return object : NetworkBoundRepository<AddCartResponse>() {
            override suspend fun fetchFromRemote(): Response<AddCartResponse> =
                apiService.discardFromCart(
                    token, categoryId, productId,
                    varientId, quantity
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
   * get cart
   * *//*
    fun getCart(
        token: String
    ): Flow<APiState<MyCartResponse>> {
        return object : NetworkBoundRepository<MyCartResponse>() {
            override suspend fun fetchFromRemote(): Response<MyCartResponse> =
                apiService.getCart(token)
        }.asFlow().flowOn(Dispatchers.IO)
    }


    *//*
  * search product
  * *//*
    fun searchProduct(
        token: String,
        keywoard: String
    ): Flow<APiState<SearchProductResponse>> {
        return object : NetworkBoundRepository<SearchProductResponse>() {
            override suspend fun fetchFromRemote(): Response<SearchProductResponse> =
                apiService.searchProduct(token, keywoard)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
 * recent search
 * *//*
    fun recentSearch(
        token: String
    ): Flow<APiState<RecentSearchListResponse>> {
        return object : NetworkBoundRepository<RecentSearchListResponse>() {
            override suspend fun fetchFromRemote(): Response<RecentSearchListResponse> =
                apiService.getRecentSearch(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
   * add recent search
   * *//*
    fun addRecentSearch(
        token: String,
        productId: Int?
    ): Flow<APiState<AddRecentSearchResponse>> {
        return object : NetworkBoundRepository<AddRecentSearchResponse>() {
            override suspend fun fetchFromRemote(): Response<AddRecentSearchResponse> =
                apiService.addRecentSearch(token, productId)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
   * delete recent search
   * *//*
    fun deleteRecentSearch(
        token: String
    ): Flow<APiState<DeleteSearchResponse>> {
        return object : NetworkBoundRepository<DeleteSearchResponse>() {
            override suspend fun fetchFromRemote(): Response<DeleteSearchResponse> =
                apiService.deleteRecentSearch(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
   * swap discarded list
   * *//*
    fun swapDiscardedList(
        token: String,
        categoryId: String
    ): Flow<APiState<ProductSwapDiscardedResponse>> {
        return object : NetworkBoundRepository<ProductSwapDiscardedResponse>() {
            override suspend fun fetchFromRemote(): Response<ProductSwapDiscardedResponse> =
                apiService.swapDiscardedList(token, categoryId)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
 * remove from cart
 * *//*
    fun removeFromCart(
        token: String,
        cartID: String
    ): Flow<APiState<MyCartResponse>> {
        return object : NetworkBoundRepository<MyCartResponse>() {
            override suspend fun fetchFromRemote(): Response<MyCartResponse> =
                apiService.removeFromCart(token, cartID)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
     * here get app info
     * *//*
    fun getAppInfo(
        advId: String,
        token: String
    ): Flow<APiState<AppInfoResponse>> {
        return object : NetworkBoundRepository<AppInfoResponse>() {
            override suspend fun fetchFromRemote(): Response<AppInfoResponse> =
                apiService.getAppInfo(token,"Android", advId)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
     * Get Profile details
     * *//*
    fun getProfile(
        token: String
    ): Flow<APiState<GetProfileResponse>> {
        return object : NetworkBoundRepository<GetProfileResponse>() {
            override suspend fun fetchFromRemote(): Response<GetProfileResponse> =
                apiService.getProfile(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
    * Get order ongoing
    * *//*
    fun getOrderOngoing(
        token: String
    ): Flow<APiState<OrderOngoingResponse>> {
        return object : NetworkBoundRepository<OrderOngoingResponse>() {
            override suspend fun fetchFromRemote(): Response<OrderOngoingResponse> =
                apiService.getOrderOngoing(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
   * Get order completed
   * *//*
    fun getOrderCompleted(
        token: String
    ): Flow<APiState<OrderHistoryResponse>> {
        return object : NetworkBoundRepository<OrderHistoryResponse>() {
            override suspend fun fetchFromRemote(): Response<OrderHistoryResponse> =
                apiService.getOrderCompleted(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
    * checkout order
    * *//*
    fun checkoutOrder(
        token: String
    ): Flow<APiState<CheckoutResponse>> {
        return object : NetworkBoundRepository<CheckoutResponse>() {
            override suspend fun fetchFromRemote(): Response<CheckoutResponse> =
                apiService.checkoutOrder(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
   * coupon list
   * *//*
    fun getCouponList(
        token: String,
        trasactionId: String?
    ): Flow<APiState<CouponListResponse>> {
        return object : NetworkBoundRepository<CouponListResponse>() {
            override suspend fun fetchFromRemote(): Response<CouponListResponse> =
                apiService.couponList(token, trasactionId)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
  * apply coupon
  * *//*
    fun applyCoupon(
        token: String,
        trasactionId: String?,
        couponCode: String?
    ): Flow<APiState<CheckoutResponse>> {
        return object : NetworkBoundRepository<CheckoutResponse>() {
            override suspend fun fetchFromRemote(): Response<CheckoutResponse> =
                apiService.applyCoupon(token, trasactionId, couponCode)
        }.asFlow().flowOn(Dispatchers.IO)

    }


    *//*
 * remove coupon
 * *//*
    fun removeCoupon(
        token: String,
        trasactionId: String?
    ): Flow<APiState<RemoveCouponResponse>> {
        return object : NetworkBoundRepository<RemoveCouponResponse>() {
            override suspend fun fetchFromRemote(): Response<RemoveCouponResponse> =
                apiService.removeCoupon(token, trasactionId)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
    * placed order
    * *//*
    fun placeOrder(
        token: String,
        deliveryDate: String?,
        timeSlot: String?,
        trasactionId: String?,
        addressId: String?
    ): Flow<APiState<PlaceOrderResponse>> {
        return object : NetworkBoundRepository<PlaceOrderResponse>() {
            override suspend fun fetchFromRemote(): Response<PlaceOrderResponse> =
                apiService.placeOrder(token, deliveryDate, timeSlot, trasactionId, addressId)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
    * get time slot
    * *//*
    fun getTimeSlot(
        token: String,
        date: String?
    ): Flow<APiState<TimeSlotResponse>> {
        return object : NetworkBoundRepository<TimeSlotResponse>() {
            override suspend fun fetchFromRemote(): Response<TimeSlotResponse> =
                apiService.getTimeSlot(token, date)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
    *cancel order
    * *//*
    fun cancelOrder(
        token: String,
        trasactionId: String?,
        reason: String?
    ): Flow<APiState<CancelOrderResponse>> {
        return object : NetworkBoundRepository<CancelOrderResponse>() {
            override suspend fun fetchFromRemote(): Response<CancelOrderResponse> =
                apiService.cancelOrder(token, trasactionId, reason)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
    * Get Address
    * *//*
    fun getAddress(
        token: String
    ): Flow<APiState<GetAddressResponse>> {
        return object : NetworkBoundRepository<GetAddressResponse>() {
            override suspend fun fetchFromRemote(): Response<GetAddressResponse> =
                apiService.getAddress(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }

    *//*
   * user logout
   * *//*
    fun userLogout(
        token: String
    ): Flow<APiState<LogoutResponse>> {
        return object : NetworkBoundRepository<LogoutResponse>() {
            override suspend fun fetchFromRemote(): Response<LogoutResponse> =
                apiService.userLogout(token)
        }.asFlow().flowOn(Dispatchers.IO)

    }


    *//*
   * update profile
   * *//*
    fun updateUserResponse(
        token: String,
        userFirstName: String?,
        userLastName: String?,
        userEmail: String?
    ): Flow<APiState<UpdateUserResponse>> {
        return object : NetworkBoundRepository<UpdateUserResponse>() {
            override suspend fun fetchFromRemote(): Response<UpdateUserResponse> =
                apiService.updateUserProfile(
                    token, userFirstName, userLastName,
                    userEmail
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * update password
    * *//*
    fun updateUserPassword(
        token: String,
        password: String?,
        oldPassword: String?
    ): Flow<APiState<UpdateUserResponse>> {
        return object : NetworkBoundRepository<UpdateUserResponse>() {
            override suspend fun fetchFromRemote(): Response<UpdateUserResponse> =
                apiService.updateUserPassword(
                    token, password, oldPassword
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
   * update phone no
   * *//*
    fun updateUserPhoneNo(
        token: String,
        phone: String?
    ): Flow<APiState<UpdatePhoneResponse>> {
        return object : NetworkBoundRepository<UpdatePhoneResponse>() {
            override suspend fun fetchFromRemote(): Response<UpdatePhoneResponse> =
                apiService.updateUserPhoneNo(
                    token, phone
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
  * update verify phone no
  * *//*
    fun updateUserVerifyPhoneNo(
        token: String,
        phone: String?,
        otp: String?
    ): Flow<APiState<UpdateVerifyPhoneResponse>> {
        return object : NetworkBoundRepository<UpdateVerifyPhoneResponse>() {
            override suspend fun fetchFromRemote(): Response<UpdateVerifyPhoneResponse> =
                apiService.updateUserVerifyPhoneNo(
                    token, phone, otp
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * update my diet pref
    * *//*
    fun updateUserDietaryPreferences(
        token: String,
        dietaryPref: String?
    ): Flow<APiState<UpdateUserResponse>> {
        return object : NetworkBoundRepository<UpdateUserResponse>() {
            override suspend fun fetchFromRemote(): Response<UpdateUserResponse> =
                apiService.updateUserDietaryPref(
                    token, dietaryPref
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * update my diet pref
    * *//*
    fun updateUserFamilyStrength(
        token: String,
        familyStrength: Int?
    ): Flow<APiState<UpdateUserResponse>> {
        return object : NetworkBoundRepository<UpdateUserResponse>() {
            override suspend fun fetchFromRemote(): Response<UpdateUserResponse> =
                apiService.updateUserFamilyStrength(
                    token, familyStrength
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * notification list
    * *//*
    fun getNotification(
        token: String,
        mark_read: String
    ): Flow<APiState<NotificationResponse>> {
        return object : NetworkBoundRepository<NotificationResponse>() {
            override suspend fun fetchFromRemote(): Response<NotificationResponse> =
                apiService.notificationList(
                    token,
                    mark_read
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }
    *//*
   * Clear All notification list
   * *//*
    fun markAllAsReadNotification(
        token: String
    ): Flow<APiState<NotificationResponse>> {
        return object : NetworkBoundRepository<NotificationResponse>() {
            override suspend fun fetchFromRemote(): Response<NotificationResponse> =
                apiService.markAllAsReadNotification(token)
        }.asFlow().flowOn(Dispatchers.IO)
    }
    *//*
 * Clear Single notification list
 * *//*
    fun markSingleAsReadNotification(
        token: String,
        id: String
    ): Flow<APiState<NotificationResponse>> {
        return object : NetworkBoundRepository<NotificationResponse>() {
            override suspend fun fetchFromRemote(): Response<NotificationResponse> =
                apiService.markSingleAsReadNotification(token,id)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
   * add address
   * *//*

    fun addAddress(
        token: String,
        fullAddress: String?,
        houseNo: String?,
        landmark: String?,
        pin: String?,
        city: String?,
        addType: String?,
        latitude: String?,
        longitude: String?,
        selectStatus: String?,
        receiverName: String?,
        receiverPhone: String?
    ): Flow<APiState<AddAddressResponse>> {
        return object : NetworkBoundRepository<AddAddressResponse>() {
            override suspend fun fetchFromRemote(): Response<AddAddressResponse> =
                apiService.addAddress(
                    token, fullAddress, houseNo, landmark, pin,city, addType, latitude,
                    longitude, selectStatus, receiverName, receiverPhone
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
  * edit address
  * *//*
    fun editAddress(
        token: String,
        addressId: String?,
        fullAddress: String?,
        houseNo: String?,
        landmark: String?,
        pin: String?,
        city: String?,
        addType: String?,
        latitude: String?,
        longitude: String?,
        selectStatus: String?,
        receiverName: String?,
        receiverPhone: String?
    ): Flow<APiState<EditAddressResponse>> {
        return object : NetworkBoundRepository<EditAddressResponse>() {
            override suspend fun fetchFromRemote(): Response<EditAddressResponse> =
                apiService.editAddress(
                    token, addressId, fullAddress, houseNo, landmark, pin,city,addType, latitude,
                    longitude, selectStatus, receiverName, receiverPhone
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
 * set default address
 * *//*
    fun setDefaultAddress(
        token: String,
        addressId: Int?
    ): Flow<APiState<EditAddressResponse>> {
        return object : NetworkBoundRepository<EditAddressResponse>() {
            override suspend fun fetchFromRemote(): Response<EditAddressResponse> =
                apiService.setDefaultAddress(
                    token, addressId
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
 * delete address
 * *//*
    fun deleteAddress(
        token: String,
        addressId: Int?
    ): Flow<APiState<DeleteAddressResponse>> {
        return object : NetworkBoundRepository<DeleteAddressResponse>() {
            override suspend fun fetchFromRemote(): Response<DeleteAddressResponse> =
                apiService.deleteAddress(
                    token, addressId
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }


    *//*
 * order details
 * *//*
    fun orderDetails(
        token: String,
        transactionId: String?
    ): Flow<APiState<OrderDetailResponse>> {
        return object : NetworkBoundRepository<OrderDetailResponse>() {
            override suspend fun fetchFromRemote(): Response<OrderDetailResponse> =
                apiService.getOrderDetails(
                    token, transactionId
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
* paymentComplete
* *//*
    fun paymentComplete(
        token: String,
        transactionId: String?,
        app_payment_id: String?,
        paymentMethod: String?,
        paymentStatus: String?
    ): Flow<APiState<PaymentCompleteResponse>> {
        return object : NetworkBoundRepository<PaymentCompleteResponse>() {
            override suspend fun fetchFromRemote(): Response<PaymentCompleteResponse> =
                apiService.paymentComplete( token, transactionId, app_payment_id, paymentMethod, paymentStatus )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
     * place search
     * *//*
    fun getPlaceSearch(
        search: String,
        key: String?,
        sessionToken: String?,
        region: String?
    ): Flow<APiState<PlaceSearchResponse>> {
        return object : NetworkBoundRepository<PlaceSearchResponse>() {
            override suspend fun fetchFromRemote(): Response<PlaceSearchResponse> =
                apiService.placeSearch(search, key, sessionToken, region)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * address from api using
    * lattitude and longitude
    * *//*
    fun getAddressFromApi(
        latlng: String,
        key: String?,
        sensor: Boolean?
    ): Flow<APiState<AddressDetailsResponse>> {
        return object : NetworkBoundRepository<AddressDetailsResponse>() {
            override suspend fun fetchFromRemote(): Response<AddressDetailsResponse> =
                apiService.getAddressFromApi(latlng, key, sensor)
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * reorder
    * *//*
    fun reOrder(
        token: String,
        transactionId: String?
    ): Flow<APiState<ReOrderResponse>> {
        return object : NetworkBoundRepository<ReOrderResponse>() {
            override suspend fun fetchFromRemote(): Response<ReOrderResponse> =
                apiService.reOrder(
                    token, transactionId
                )
        }.asFlow().flowOn(Dispatchers.IO)
    }

    *//*
    * city list
    **//*

    fun getCityList(): Flow<APiState<GetCityResponse>> {
        return object : NetworkBoundRepository<GetCityResponse>() {
            override suspend fun fetchFromRemote(): Response<GetCityResponse> =
                apiService.getCityList()
        }.asFlow().flowOn(Dispatchers.IO)
    }*/
}