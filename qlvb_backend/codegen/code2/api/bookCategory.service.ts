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
 *//* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';
import { CustomHttpUrlEncodingCodec }                        from '../encoder';

import { Observable }                                        from 'rxjs';

import { BookCategory } from '../model/bookCategory';
import { Pageable } from '../model/pageable';
import { ResponseError } from '../model/responseError';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class BookCategoryService {

    protected basePath = 'http://localhost:2221';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (const consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * Get all book
     * 
     * @param pageable 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findAll1(pageable: Pageable, observe?: 'body', reportProgress?: boolean): Observable<Array<BookCategory>>;
    public findAll1(pageable: Pageable, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<BookCategory>>>;
    public findAll1(pageable: Pageable, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<BookCategory>>>;
    public findAll1(pageable: Pageable, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (pageable === null || pageable === undefined) {
            throw new Error('Required parameter pageable was null or undefined when calling findAll1.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (pageable !== undefined && pageable !== null) {
            queryParameters = queryParameters.set('pageable', <any>pageable);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.request<Array<BookCategory>>('get',`${this.basePath}/api/book-category`,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}