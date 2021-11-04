# AvatarApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**avatarNumGet**](AvatarApi.md#avatarNumGet) | **GET** /avatar/{num} | Get avatar image for number


<a name="avatarNumGet"></a>
# **avatarNumGet**
> avatarNumGet(num)

Get avatar image for number

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AvatarApi;


AvatarApi apiInstance = new AvatarApi();
BigDecimal num = new BigDecimal(); // BigDecimal | ID number of avatar
try {
    apiInstance.avatarNumGet(num);
} catch (ApiException e) {
    System.err.println("Exception when calling AvatarApi#avatarNumGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **num** | **BigDecimal**| ID number of avatar |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

