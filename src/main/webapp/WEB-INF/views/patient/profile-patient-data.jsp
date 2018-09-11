<%-- 
    Document   : profile-patient-data
    Created on : 31/08/2018, 12:01:25
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="application/json"%>
(patientProfile:
    [patient:
        {name:${patientProfile.patientName}},
        {cpf:${patientProfile.patientCpf}},
        {email:${patientProfile.patientProfileEmail}},
        {genre:${patientProfile.patientProfileGenre}},
        {birthDate:${patientProfile.patientProfileBirthDate.time}},
        {height:${patientProfile.patientProfileHeight}},
        {bloodType:${patientProfile.patientProfileBloodType}},
        {telephone:${patientProfile.patientProfileTelephone}}        
        <c:if test="${patientProfile.file != null}">
            ,
            {imageId:${patientProfile.file.fileId}},
            {imageLength:${patientProfile.file.fileLength}}
        </c:if>
    ]
)