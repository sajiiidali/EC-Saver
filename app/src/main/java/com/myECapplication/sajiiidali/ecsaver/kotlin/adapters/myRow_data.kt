package com.myECapplication.sajiiidali.ecsaver.kotlin.adapters

class myRow_data {

    lateinit var EC_TYPE: String
    lateinit var EC_NUMBER:String
    lateinit var DATE:String
    lateinit var DAYOFF:String

    constructor(EC_TYPE: String, EC_NUMBER: String, DATE: String) {
        this.EC_TYPE = EC_TYPE
        this.EC_NUMBER = EC_NUMBER
        this.DATE = DATE
    }

    constructor(DATE: String, DAYOFF: String) {
        this.DATE = DATE
        this.DAYOFF = DAYOFF
    }

}