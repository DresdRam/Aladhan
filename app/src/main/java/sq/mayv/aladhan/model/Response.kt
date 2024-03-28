package sq.mayv.aladhan.model

data class Response<Template>(
    val code: Int = 0,
    val status: String = "",
    val data: Template
)
