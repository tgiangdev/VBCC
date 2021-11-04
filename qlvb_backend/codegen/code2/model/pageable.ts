/**
 * Contact Application API
 * This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { Sort } from './sort';

export interface Pageable { 
    offset?: number;
    sort?: Sort;
    pageNumber?: number;
    pageSize?: number;
    paged?: boolean;
    unpaged?: boolean;
}