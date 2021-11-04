# BooksslowApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**booksIsbnSlowGet**](BooksslowApi.md#booksIsbnSlowGet) | **GET** /books/{isbn}/slow | Get a single book by ISBN, but with a 5s delay
[**booksSlowGet**](BooksslowApi.md#booksSlowGet) | **GET** /books/slow | Get all books, but with a 5s delay


<a name="booksIsbnSlowGet"></a>
# **booksIsbnSlowGet**
> Book booksIsbnSlowGet(isbn)

Get a single book by ISBN, but with a 5s delay

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksslowApi;


BooksslowApi apiInstance = new BooksslowApi();
String isbn = "isbn_example"; // String | ISBN of the book
try {
    Book result = apiInstance.booksIsbnSlowGet(isbn);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksslowApi#booksIsbnSlowGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **isbn** | **String**| ISBN of the book |

### Return type

[**Book**](Book.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="booksSlowGet"></a>
# **booksSlowGet**
> List&lt;Book&gt; booksSlowGet()

Get all books, but with a 5s delay

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksslowApi;


BooksslowApi apiInstance = new BooksslowApi();
try {
    List<Book> result = apiInstance.booksSlowGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksslowApi#booksSlowGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Book&gt;**](Book.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

