package com.shankarlohar.teamvinayak.model

import com.shankarlohar.teamvinayak.util.Details
import com.shankarlohar.teamvinayak.util.Role
import com.shankarlohar.teamvinayak.util.Status

data class UserData(
    val uid: String = "",
    val noOfPeopleReferred: Int = 0,
    val role: Role = Role.MEMBER, // Admin, Member
    val personalDetails: PersonalDetails = PersonalDetails(),
    val emergencyContact: EmergencyContact = EmergencyContact(),
    val disability: Disability = Disability(),
    val referral: String = "", // If someone referred, existing member
    val membership: Membership = Membership(),
    val parq: PARQ = PARQ()

)

data class EmergencyContact(
    val name: String = "",
    val number: String = "+91 ",
    val profession: String = "",
)

data class PersonalDetails(
    val picture: String = "",
    val fullName: String = "",
    val fullAddress: String = "",
    val mobile: String = "+91 ",
    val dateOfBirth: String = "",
    val gender: String = "",
    val aadhaarNumber: String = "",
    val currentWeight: String = "",
    val currentHeight: String = "",
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val profession: String = "",
)

data class Disability(
    val hasDisability: Boolean = false, // kept true to show the field, false disables the questions
    val about: String = "",
    val doctorName: String = "",
    val doctorContact: String = ""
)

data class Membership(
    val formSubmission: String = "", // Date and Time Stamp
    val status: Status = Status.PENDING, // Active, Pending, Blocked
    val details: Details = Details.UNDER_REVIEW // Approved, Under Review
)

data class PARQ(
    val question1: String = "Have you for any reason, been unable to exercise in the past?",
    val answer1: Boolean = false,
    val question2: String = "Has your Physician ever advised you against exercising?",
    val answer2: Boolean = false,
    val question3: String = "Have you ever suffered from any cardiac (heart) related illness?",
    val answer3: Boolean = false,
    val question4: String = "Have you ever suffered from respiratory difficulties?",  //
    val answer4: Boolean = false,
    val question5: String = "Have you ever suffered from fainting, migraines, or loss of balance?",
    val answer5: Boolean = false,
    val question6: String = "Have you ever suffered from any bone, joint, or muscle related disease?",
    val answer6: Boolean = false,
    val question7: String = "Is there any heart disease history in your family?",
    val answer7: Boolean = false,
    val question8: String = "Have you experienced chest pain whilst exercising?",
    val answer8: Boolean = false,
    val question9: String = "Do you have high blood pressure?",
    val answer9: Boolean = false,
    val question10: String = "Do you have elevated cholesterol levels?",
    val answer10: Boolean = false,
)





