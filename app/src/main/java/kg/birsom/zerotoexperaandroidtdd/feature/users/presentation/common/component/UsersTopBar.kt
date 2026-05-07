package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppCardBackground
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppTextPrimary
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppTextSecondary
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UsersTopBar(
    title: String,
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    subtitle: String? = null,
    subtitleModifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    backButtonModifier: Modifier = Modifier,
    action: @Composable RowScope.() -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AppCardBackground)
            .padding(horizontal = 20.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick,
                    modifier = backButtonModifier
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = stringResource(R.string.content_description_back),
                        tint = AppTextPrimary
                    )
                }
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = AppTextPrimary,
                modifier = titleModifier.weight(1f)
            )

            action()
        }

        if (subtitle != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = AppTextSecondary,
                modifier = subtitleModifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersTopBarPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersTopBar(
            title = stringResource(R.string.users_title),
            subtitle = stringResource(R.string.users_subtitle, 3)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersTopBarBackPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersTopBar(
            title = stringResource(R.string.user_details_title),
            onBackClick = {}
        )
    }
}