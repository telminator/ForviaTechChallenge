package com.example.challenge.domain.usecase.apps

import javax.inject.Inject

class AppDownloadUseCase @Inject constructor() {
    operator fun invoke(appId: Int): Result<String> {
        // Since we only want to show the error, return failure. (else we'd invoke the download action from the repository)
        return Result.failure(UnsupportedOperationException("Download is not available in demo mode"))
    }
}