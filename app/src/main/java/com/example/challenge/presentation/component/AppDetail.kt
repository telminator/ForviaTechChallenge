package com.example.challenge.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.challenge.R
import com.example.challenge.domain.model.App
@Composable
 fun AppDetail(
    app: App,
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App graphic banner (if available)
        app.graphicUrl?.let { url ->
            AsyncImage(
                model = url,
                contentDescription = stringResource(
                    R.string.app_banner_content_description,
                    app.name
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // App icon and name
        AsyncImage(
            model = app.iconUrl,
            contentDescription = stringResource(R.string.app_icon_content_description, app.name),
            modifier = Modifier
                .height(80.dp)
                .padding(8.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = app.name,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.version_label, app.versionName),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.rating_label, app.rating),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.downloads_label, app.downloads),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(4.dp))


        Text(
            text = stringResource(
                id = R.string.size_label,
                when {
                    app.size >= 1024 * 1024 -> {
                        val mbSize = app.size / (1024.0 * 1024.0)
                        stringResource(R.string.size_megabytes, mbSize.toFloat())
                    }
                    else -> {
                        val kbSize = app.size / 1024.0
                        stringResource(R.string.size_kilobytes, kbSize.toFloat())
                    }
                }
            ),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onDownloadClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Icon(
                imageVector = Icons.Filled.Download,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.download_button))
        }
    }
}
