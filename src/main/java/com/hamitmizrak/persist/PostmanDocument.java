package com.hamitmizrak.persist;

public class PostmanDocument {
    // CREATE
    // http://localhost:2222/customer/api/v1/create
   /*
        POST /customer/api/v1/create HTTP/1.1
        Host: localhost:2222
        Content-Type: application/json
        Content-Length: 121

        {
            "name":"Hamit",
            "surname":"Mızrak",
            "email":"hamitmizrak45444@gmail.com",
            "password":"Hm123456@"
        }
    */

    // LIST
    //http://localhost:2222/customer/api/v1/list
    /*
        GET /customer/api/v1/list HTTP/1.1
        Host: localhost:2222
    */

    //FIND
    // http://localhost:2222/customer/api/v1/find/-1
    /*

    GET /customer/api/v1/find/-1 HTTP/1.1
    Host: localhost:2222
    */

    //UPDATE
    //http://localhost:2222/customer/api/v1/update/1
    /*
    PUT /customer/api/v1/update/3HTTP/1.1
    Host:localhost:2222
    Content-Type:application/json
    Content-Length:125

    {
        "name":"Hamit22",
            "surname":"Mızrak22",
            "email":"hamitmizrak44522@gmail.com",
            "password":"Hm123456@"
    }
    */

    //DELETE
    // http://localhost:2222/customer/api/v1/delete/1
    /*
    DELETE /customer/api/v1/delete/1 HTTP/1.1
    Host: localhost:2222
    */


}
