package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsScreenTags
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsTestUiData
import kg.birsom.zerotoexperaandroidtdd.ui.theme.AppCardBackground
import kg.birsom.zerotoexperaandroidtdd.ui.theme.AppTextPrimary
import kg.birsom.zerotoexperaandroidtdd.ui.theme.AppTextSecondary
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UserDetailsInfoCard(
    title: String,
    rows: List<UserDetailsInfoRow>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = AppCardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = AppTextPrimary
            )

            Spacer(modifier = Modifier.height(14.dp))

            rows.forEachIndexed { index, row ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(14.dp))
                }

                UserDetailsTextRow(row = row)
            }
        }
    }
}

data class UserDetailsInfoRow(
    val label: String,
    val value: String,
    val testTag: String
)

@Composable
private fun UserDetailsTextRow(
    row: UserDetailsInfoRow
) {
    Column {
        Text(
            text = row.label,
            style = MaterialTheme.typography.labelLarge,
            color = AppTextSecondary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = row.value,
            style = MaterialTheme.typography.bodyLarge,
            color = AppTextPrimary,
            modifier = Modifier.testTag(row.testTag)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserDetailsInfoCardPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserDetailsInfoCard(
            title = stringResource(R.string.user_details_section_contacts),
            rows = listOf(
                UserDetailsInfoRow(
                    label = stringResource(R.string.user_details_label_email),
                    value = UserDetailsTestUiData.leanne.email,
                    testTag = UserDetailsScreenTags.TEXT_DETAILS_EMAIL
                ),
                UserDetailsInfoRow(
                    label = stringResource(R.string.user_details_label_phone),
                    value = UserDetailsTestUiData.leanne.phone,
                    testTag = UserDetailsScreenTags.TEXT_DETAILS_PHONE
                )
            )
        )
    }
}