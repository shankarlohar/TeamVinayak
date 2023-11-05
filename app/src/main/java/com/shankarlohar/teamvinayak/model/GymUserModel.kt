package com.shankarlohar.teamvinayak.model

data class GymUserModel(
    var termsAndConditionsAgreed: Boolean? = null,

    var userId: String? = null,

    var personalDetails: PersonalDetails? = null,

    var consentToExercise: ConsentToExercise? = null,


    var emergencyContact: EmergencyContact? = null,
    var doctorDetails: DoctorDetails? = null,

    var membershipPlan: String? = null,


    var declaration: Boolean? = null,
    var referralSources: String? = null

)

data class PersonalDetails (
    var profilePictureUrl: String? = null,
    var fullName: String? = null,
    var address: String? = null,
    var mobileNumber: String? = null,
    var dateOfBirth: String? = null,
    var weight: Double? = null,
    var height: Int? = null,
    var gender: String? = null,
    var disability: String? = null,
    var fitnessGoal: String? = null,
    var joiningDate: String? = null,
)

data class EmergencyContact(
    var contactName: String? = null,
    var relationship: String? = null,
    var contactPhoneNumber: String? = null,
    var contactAddress: String? = null
)

data class ConsentToExercise(
    var unableToExerciseInPast: Boolean? = null,
    var physicianAdvisedAgainstExercising: Boolean? = null,
    var sufferedFaintingMigrainesLossOfBalance: Boolean? = null,
    var boneJointMuscleRelatedDisease: Boolean? = null,
    var chestPainWhileExercising: Boolean? = null,
    var bloodPressure: Boolean? = null,
    var elevatedCholesterolLevels: Boolean? = null,
    var takingPrescribedMedication: Boolean? = null,
    var familyHeartDiseaseHistory: Boolean? = null,
    var sufferedCardiacRelatedIllness: Boolean? = null,
    var sufferedRespiratoryDifficulties: Boolean? = null,
    var additionalInformation: String? = null
)

data class DoctorDetails(
    var doctorName: String? = null,
    var doctorContactNumber: String? = null,
    var doctorEmail: String? = null
)
