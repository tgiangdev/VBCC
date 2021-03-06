/**
 * Contact Application API
 * This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
import { Account } from './account';


export interface JwtAuthenticationResponse { 
    accessToken?: string;
    tokenType?: string;
    account?: Account;
}

