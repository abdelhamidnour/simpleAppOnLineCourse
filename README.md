# simpleAppOnLineCourse
simple Spring boot app 
Deployment Instructions

•	it is a maven project 
•	Import to eclipse or just deploy the jar file (take in mind Data base Configurations )
•	Run the following queries after the project is running 
•	 (user credential is user:user@user.com password:1)


db.role.insert({"_id":"5c9732e206b6ac188cfb1738","role":"ROLE_USER","_class":"com.restaurantApp.model.Role"})
db.role.insert({"_id":"5c97339806b6ac188cfb173a","role":"ROLE_ADMIN","_class":"com.restaurantApp.model.Role"})

db.user.insert({"email":"user@user.com","password":"$2a$10$YBsAz8PvrxtIXvFmxov9POBjeHWnhCHUKWJLUHEsi.mPXsxhbHi7q","username":"user","dob":"123","enabled":true,"gender":"male","roles":[{"$ref":"role","$id":"5c9732e206b6ac188cfb1738","$db":""}],"_class":"com.restaurantApp.model.User"})



db.course.insert ({"courseName":"java","description":"simple coures for angular js","publishDate":"2019-05-17T21:35:05.868Z","totalHours":"64","instructor":"abdelhamid","registered":false,"_class":"com.simpleApp.model.Course"})
db.course.insert ({"courseName":"Sprin","description":"Spring ","publishDate":"2019-05-17T21:35:05.868Z","totalHours":"64","instructor":"abdelhamid","registered":false,"_class":"com.simpleApp.model.Course"})
db.course.insert ({"courseName":"nodeJS","description":"simple coures for node js","publishDate":"2019-05-17T21:35:05.868Z","totalHours":"64","instructor":"abdelhamid","registered":false,"_class":"com.simpleApp.model.Course"})
db.course.insert ({"courseName":"mongoDB","description":"simple coures for mongo DB","publishDate":"2019-05-17T21:35:05.868Z","totalHours":"64","instructor":"abdelhamid","registered":false,"_class":"com.simpleApp.model.Course"})






