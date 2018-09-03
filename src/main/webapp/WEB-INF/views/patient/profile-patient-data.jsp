<%-- 
    Document   : profile-patient-data
    Created on : 31/08/2018, 12:01:25
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>
(patientProfile:
    [patient:
        {name:${patientProfile.patientName}},
        {cpf:${patientProfile.patientCpf}},
        {email:${patientProfile.patientProfileEmail}},
        {genre:${patientProfile.patientProfileGenre}},
        {birthDate:${patientProfile.patientProfileBirthDate}},
        {height:${patientProfile.patientProfileHeight}},
        {bloodType:${patientProfile.patientProfileBloodType}},
        {telephone:${patientProfile.patientProfileTelephone}}
    ]
)