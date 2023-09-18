# Time Appointment Reservation Application

## Description of the TimeAppointment
This application is reliable for the reservations for a service or where this is neccesary.

User can reserve only in 30 mins intervals.
Maximum reservation must be 3 hours not more.
The open hours is 9:00-17:00.

The service must be finished at latest 17:00. 
If You want to reserve more hours that is out of 5pm, not possible. Reserve for tomorrow.

Welcome on us.

### 1. For QA Engineers

<table>
    <tr>
        <th>What</th>
        <th>URL</th>
        <th>Other information</th>
    </tr>
    <tr>
        <td>H2-console</td>
        <td>http://localhost:8080/h2-console</td>
        <td>login name: sa</td>
    </tr>
    <tr>
        <td rowspan="3">REST API ENDPOINT</td>
        <td>/appointment/create</td>
        <td>POST request, use the json files to test in the resources/json</td>
    </tr>
    <tr>
        <td>/appointment/all</td>
        <td>GET request</td>
    </tr>
    <tr>
        <td>/appointment/delete</td>
        <td>DELETE request</td>
    </tr>
</table>

To testing use the Postman or Insomnia application.

#### 2. Test datas info

Files location in the resources/json

<table>
    <tr>
        <th>test type</th>
        <th>test file name</th>
        <th>description</th>
    </tr>
    <tr>
        <td rowspan="4">Positive test</td>
        <td>reservation1.json</td>
        <td>First client to reserve from 9:30 to 11:30 on 18/09/2023 </td>
    </tr>
    <tr>
        <td>reservation2.json</td>
        <td>Second client to reserve from 11:30 to 13:00 on 18/09/2023 </td>
    </tr>
    <tr>
        <td>reservation3.json</td>
        <td>Third client to reserve from 13:00 to 15:00 on 18/09/2023 </td>
    </tr>
    <tr>
        <td>reservation4.json</td>
        <td>Third client to reserve from 15:00 to 17:00 on 18/09/2023 </td>
    </tr>
    <tr>
        <td rowspan="5">Negative test</td>
        <td>overlapping.json</td>
        <td>First client to reserve from 12:30 to 13:30 on 18/09/2023 </td>
    </tr>
    <tr>
        <td>moreThanThreeHours.json</td>
        <td>First client to reserve from 10:00 to 14:30 on 18/09/2023 </td>
    </tr>
    <tr>
        <td>notStartedCorrectTime.json</td>
        <td>The 9:45 is not good,because this is not 30 mins cycle. </td>
    </tr>
    <tr>
        <td>outOfInterval1.json</td>
        <td>The beginning time is 8:30</td>
    </tr>
    <tr>
        <td>outOfInterval2.json</td>
        <td>The to date time is 19:30</td>
    </tr>
</table>