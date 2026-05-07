package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data

object UsersScreenTags {

    const val BOX_USERS_LOADING = "box_users_loading"
    const val PROGRESS_USERS_LOADING = "progress_users_loading"
    const val CARD_USERS_ERROR = "card_users_error"
    const val TEXT_USERS_ERROR_MESSAGE = "text_users_error_message"
    const val BUTTON_USERS_RETRY = "button_users_retry"

    const val BOX_USERS_OFFLINE_BANNER = "box_users_offline_banner"
    const val TEXT_USERS_OFFLINE_BANNER = "text_users_offline_banner"

    fun cardUserItem(position: Int) = "card_users_item_$position"

    fun imageUserAvatar(position: Int) = "image_users_item_avatar_$position"

    fun textUserAvatarInitials(position: Int) =
        "text_users_item_avatar_initials_$position"

    fun textUserName(position: Int) = "text_users_item_name_$position"

    fun textUserEmail(position: Int) = "text_users_item_email_$position"
}