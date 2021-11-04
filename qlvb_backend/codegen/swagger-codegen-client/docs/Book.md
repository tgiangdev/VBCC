
# Book

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**isbn** | **String** | ISBN, which is used as identifier - only numbers are allowed (no hyphens) | 
**title** | **String** | Title of the book | 
**authors** | **List&lt;String&gt;** | List of all authors of the book |  [optional]
**subtitle** | **String** | Subtitle of the book |  [optional]
**rating** | **Integer** | Rating of the book as stars, from one star (bad) to five stars (great) |  [optional]
**published** | [**OffsetDateTime**](OffsetDateTime.md) | date-time as defined by RFC3339 (http://www.ietf.org/rfc/rfc3339.txt) - like new Date().toISOString(); |  [optional]
**description** | **String** | Short description of the book |  [optional]
**thumbnails** | [**List&lt;Thumbnail&gt;**](Thumbnail.md) | Images of the book, which are used as thumbnails |  [optional]
**price** | [**BigDecimal**](BigDecimal.md) | Price of the book |  [optional]
**firstThumbnailUrl** | **String** | URL of the first thumbnail, just a shorthand |  [optional]



