package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data

object UsersScreenTags {

    const val SURFACE_USERS_SCREEN = "surface_users_screen"
    const val COLUMN_USERS_CONTENT = "column_users_content"
    const val LAZY_COLUMN_USERS = "lazy_column_users"

    const val COLUMN_USERS_HEADER = "column_users_header"
    const val TEXT_USERS_TITLE = "text_users_title"
    const val TEXT_USERS_SUBTITLE = "text_users_subtitle"
    const val TEXT_USERS_COUNT = "text_users_count"

    const val BOX_USERS_LOADING = "box_users_loading"
    const val COLUMN_USERS_LOADING = "column_users_loading"
    const val PROGRESS_USERS_LOADING = "progress_users_loading"
    const val TEXT_USERS_LOADING = "text_users_loading"

    const val CARD_USERS_ERROR = "card_users_error"
    const val TEXT_USERS_ERROR_TITLE = "text_users_error_title"
    const val TEXT_USERS_ERROR_MESSAGE = "text_users_error_message"
    const val BUTTON_USERS_RETRY = "button_users_retry"

    const val BOX_USERS_OFFLINE_BANNER = "box_users_offline_banner"
    const val TEXT_USERS_OFFLINE_BANNER = "text_users_offline_banner"

    fun cardUserItem(position: Int) = "card_users_item_$position"

    fun rowUserItem(position: Int) = "row_users_item_$position"

    fun columnUserItemContent(position: Int) = "column_users_item_content_$position"

    fun imageUserAvatar(position: Int) = "image_users_item_avatar_$position"

    fun textUserAvatarInitials(position: Int) =
        "text_users_item_avatar_initials_$position"

    fun textUserName(position: Int) = "text_users_item_name_$position"

    fun textUserEmail(position: Int) = "text_users_item_email_$position"

    fun iconUserArrow(position: Int) = "icon_users_item_arrow_$position"
}