package com.ops.flipclass.models

class User {

    var name: String? = null
    var email: String? = null
    var uid: String? = null
    var status: String? = null
    var photo: String? = null

    constructor()

    constructor(name: String?, email: String?, uid: String?, status: String?, photo: String?){
        this.name = name
        this.email = email
        this.uid = uid
        this.status = status
        this.photo = photo
    }
}