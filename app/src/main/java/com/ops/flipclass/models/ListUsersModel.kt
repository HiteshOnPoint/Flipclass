package com.ops.flipclass.models

data class ListUsersModel(
    var `data`: List<Data>,
    var page: Int,
    var per_page: Int,
    var support: Support,
    var total: Int,
    var total_pages: Int
) {
    data class Data(
        var avatar: String,
        var email: String,
        var first_name: String,
        var id: Int,
        var last_name: String,
        var last_message: String,
        var time_ago: String,
        var unseen_count: Int,
    )

    data class Support(
        var text: String,
        var url: String
    )
}