package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {

    data class Res(
        @StringRes val id: Int,
        val args: List<Any> = emptyList()
    ) : UiText
}

@Composable
fun UiText.asString(): String {
    return when (this) {
        is UiText.Res -> if (args.isEmpty()) {
            stringResource(id)
        } else {
            stringResource(id, *args.toTypedArray())
        }
    }
}