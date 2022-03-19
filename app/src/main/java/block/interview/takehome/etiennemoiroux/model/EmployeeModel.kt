package block.interview.takehome.etiennemoiroux.model

class EmployeeModel(
    val uuid: String?,
    val full_name: String?,
    val phone_number: String?,
    val email_address: String?,
    val biography: String?,
    val photo_url_small: String?,
    val photo_url_large: String?,
    val team: String?,
    val employee_type: String?
) {
    fun valid(): Boolean {
        return (uuid != null && full_name != null && email_address != null && team != null && employee_type != null)
    }
}