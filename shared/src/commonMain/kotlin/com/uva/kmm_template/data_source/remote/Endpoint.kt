package com.uva.kmm_template.data_source.remote

interface Endpoint {
    val title: String
    val url: String
}

object Endpoints {

    val DEV: Endpoint =
        EndpointImpl(
            title = "Dev",
            url = "https://api.openai.com/v1/"
        )

    private data class EndpointImpl(
        override val title: String,
        override val url: String
    ) : Endpoint
}
