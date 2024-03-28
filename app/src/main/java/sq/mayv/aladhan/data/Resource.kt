package sq.mayv.aladhan.data

class Resource<Template>(
    var data: Template? = null,
    var exception: Exception = Exception("Resource Exception!")
)