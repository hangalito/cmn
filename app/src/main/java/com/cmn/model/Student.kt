package com.cmn.model

/** Data class that represent each student in the app.
 *
 * @param name the name of the student
 * @param surname the surname of the student
 * @param grade the current grade of the student
 * @param className the class name of the student
 * @param email the email of the student
 * @param phone the phone number of the student
 */
data class Student(
    val name: String,
    val surname: String,
    val grade: Int,
    val className: String,
    val email: String?,
    val phone: Number?
)
