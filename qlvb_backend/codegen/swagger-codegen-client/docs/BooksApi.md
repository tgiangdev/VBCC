# BooksApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**booksDelete**](BooksApi.md#booksDelete) | **DELETE** /books | RESET store to initial state
[**booksGet**](BooksApi.md#booksGet) | **GET** /books | Get all books
[**booksIsbnCheckGet**](BooksApi.md#booksIsbnCheckGet) | **GET** /books/{isbn}/check | Return whether ISBN exists or not
[**booksIsbnDelete**](BooksApi.md#booksIsbnDelete) | **DELETE** /books/{isbn} | Delete a book
[**booksIsbnGet**](BooksApi.md#booksIsbnGet) | **GET** /books/{isbn} | Get a single book by ISBN
[**booksIsbnPut**](BooksApi.md#booksIsbnPut) | **PUT** /books/{isbn} | Update an existing book
[**booksIsbnRatePost**](BooksApi.md#booksIsbnRatePost) | **POST** /books/{isbn}/rate | Update rating of a book to a given value
[**booksPost**](BooksApi.md#booksPost) | **POST** /books | Create a new book
[**booksSearchSearchTermGet**](BooksApi.md#booksSearchSearchTermGet) | **GET** /books/search/{searchTerm} | Get all books matching the given search term (case insensitive). The properties isbn, title, authors, published (interpreted as ISO string), subtitle and description are evaluated for a match.
[**secureBooksGet**](BooksApi.md#secureBooksGet) | **GET** /secure/books | Get all books - SECURED via OAuth 2.0


<a name="booksDelete"></a>
# **booksDelete**
> booksDelete()

RESET store to initial state

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksApi;


BooksApi apiInstance = new BooksApi();
try {
    apiInstance.booksDelete();
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#booksDelete");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="booksGet"></a>
# **booksGet**
> List&lt;Book&gt; booksGet()

Get all books

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksApi;


BooksApi apiInstance = new BooksApi();
try {
    List<Book> result = apiInstance.booksGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#booksGet");
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

<a name="booksIsbnCheckGet"></a>
# **booksIsbnCheckGet**
> booksIsbnCheckGet(isbn)

Return whether ISBN exists or not

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksApi;


BooksApi apiInstance = new BooksApi();
String isbn = "isbn_example"; // String | ISBN of the book
try {
    apiInstance.booksIsbnCheckGet(isbn);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#booksIsbnCheckGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **isbn** | **String**| ISBN of the book |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="booksIsbnDelete"></a>
# **booksIsbnDelete**
> booksIsbnDelete(isbn)

Delete a book

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksApi;


BooksApi apiInstance = new BooksApi();
String isbn = "isbn_example"; // String | ISBN of the book to delete
try {
    apiInstance.booksIsbnDelete(isbn);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#booksIsbnDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **isbn** | **String**| ISBN of the book to delete |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="booksIsbnGet"></a>
# **booksIsbnGet**
> Book booksIsbnGet(isbn)

Get a single book by ISBN

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksApi;


BooksApi apiInstance = new BooksApi();
String isbn = "isbn_example"; // String | ISBN of the book
try {
    Book result = apiInstance.booksIsbnGet(isbn);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#booksIsbnGet");
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

<a name="booksIsbnPut"></a>
# **booksIsbnPut**
> Book booksIsbnPut(isbn, book)

Update an existing book

Requires a full book entry, ISBN in query and body must match

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksApi;


BooksApi apiInstance = new BooksApi();
String isbn = "isbn_example"; // String | ISBN of the book to update
Book book = new Book(); // Book | An existing book to update
try {
    Book result = apiInstance.booksIsbnPut(isbn, book);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#booksIsbnPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **isbn** | **String**| ISBN of the book to update |
 **book** | [**Book**](Book.md)| An existing book to update |

### Return type

[**Book**](Book.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="booksIsbnRatePost"></a>
# **booksIsbnRatePost**
> booksIsbnRatePost(isbn, rating)

Update rating of a book to a given value

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksApi;


BooksApi apiInstance = new BooksApi();
String isbn = "isbn_example"; // String | ISBN of the book
Rating rating = new Rating(); // Rating | The new rating value for the given book
try {
    apiInstance.booksIsbnRatePost(isbn, rating);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#booksIsbnRatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **isbn** | **String**| ISBN of the book |
 **rating** | [**Rating**](Rating.md)| The new rating value for the given book |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="booksPost"></a>
# **booksPost**
> Book booksPost(book)

Create a new book

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksApi;


BooksApi apiInstance = new BooksApi();
Book book = new Book(); // Book | A new book to be stored
try {
    Book result = apiInstance.booksPost(book);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#booksPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **book** | [**Book**](Book.md)| A new book to be stored |

### Return type

[**Book**](Book.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="booksSearchSearchTermGet"></a>
# **booksSearchSearchTermGet**
> List&lt;Book&gt; booksSearchSearchTermGet(searchTerm)

Get all books matching the given search term (case insensitive). The properties isbn, title, authors, published (interpreted as ISO string), subtitle and description are evaluated for a match.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.BooksApi;


BooksApi apiInstance = new BooksApi();
String searchTerm = "searchTerm_example"; // String | search term (treated case insensitive)
try {
    List<Book> result = apiInstance.booksSearchSearchTermGet(searchTerm);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#booksSearchSearchTermGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **searchTerm** | **String**| search term (treated case insensitive) |

### Return type

[**List&lt;Book&gt;**](Book.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="secureBooksGet"></a>
# **secureBooksGet**
> List&lt;Book&gt; secureBooksGet()

Get all books - SECURED via OAuth 2.0

Please authorize first! User: auth@angular.schule Pass: auth0123

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.BooksApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: Auth0
OAuth Auth0 = (OAuth) defaultClient.getAuthentication("Auth0");
Auth0.setAccessToken("YOUR ACCESS TOKEN");

BooksApi apiInstance = new BooksApi();
try {
    List<Book> result = apiInstance.secureBooksGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BooksApi#secureBooksGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Book&gt;**](Book.md)

### Authorization

[Auth0](../README.md#Auth0)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

