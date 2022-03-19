package block.interview.takehome.etiennemoiroux.data.mock

import block.interview.takehome.etiennemoiroux.model.EmployeeModel

object MockEmployeeDataSet {

    val emptyEmployees = listOf<EmployeeModel>(
    )

    val malformedEmployees = listOf(
        EmployeeModel(
            uuid = "0d8fcc12-4d0c-425c-8355-390b312b909c",
            full_name = "Justine Mason",
            phone_number = "5553280123",
            email_address = "jmason.mock@squareup.com",
            biography = "Engineer on the Point of Sale team.",
            photo_url_small = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
            photo_url_large = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
            team = "Point of Sale",
            employee_type = "FULL_TIME"
        ),
        EmployeeModel(
            uuid = "0d8fcc12-4d0c-425c-8355-390b312bffff",
            full_name = null,
            phone_number = "asdasddd",
            email_address = "etienne.mock@me.com",
            biography = "Engineer on the Point of Sale team.",
            photo_url_small = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
            photo_url_large = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
            team = "Point of Sale",
            employee_type = "FULL_TIME"
        ),
        EmployeeModel(
            uuid = null,
            full_name = null,
            phone_number = "5553280123",
            email_address = "bob.demo@squareup.com",
            biography = "Engineer on the Point of Sale team.",
            photo_url_small = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
            photo_url_large = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
            team = "Point of Sale",
            employee_type = "FULL_TIME"
        )
    )

    val employees = listOf(
        EmployeeModel(
            uuid = "0d8fcc12-4d0c-425c-8355-390b312b909c",
            full_name = "Justine Mason",
            phone_number = "5553280123",
            email_address = "jmason.mock@squareup.com",
            biography = "Engineer on the Point of Sale team.",
            photo_url_small = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
            photo_url_large = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
            team = "Point of Sale",
            employee_type = "FULL_TIME"
        ),
        EmployeeModel(
            uuid = "0d8fcc12-4d0c-425c-8355-390b312bffff",
            full_name = "Etienne demo",
            phone_number = "asdasddd",
            email_address = "etienne.mock@me.com",
            biography = "Engineer on the Point of Sale team.",
            photo_url_small = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
            photo_url_large = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
            team = "Point of Sale",
            employee_type = "FULL_TIME"
        ),
        EmployeeModel(
            uuid = "0d8fcc12-4d0c-425c-8355-fff312bffff",
            full_name = "bob jojo",
            phone_number = "5553280123",
            email_address = "bob.demo@squareup.com",
            biography = "Engineer on the Point of Sale team.",
            photo_url_small = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
            photo_url_large = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
            team = "Point of Sale",
            employee_type = "FULL_TIME"
        )
    )
}
