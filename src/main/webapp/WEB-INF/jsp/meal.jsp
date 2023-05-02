<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://calc.nutrition.com/functions" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><fmt:message key="meal.title"/></h3>

    <form method="get" action="meals/filter">
        <dl>
            <dt><fmt:message key="meal.startDate"/>:</dt>
            <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="meal.endDate"/>:</dt>
            <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="meal.startTime"/>:</dt>
            <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="meal.endTime"/>:</dt>
            <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
        </dl>
        <button type="submit"><fmt:message key="meal.filter"/></button>
    </form>
    <hr>
    <a href="meals/create"><fmt:message key="meal.add"/></a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="meal.dateTime"/></th>
            <th><fmt:message key="meal.description"/></th>
            <th><fmt:message key="meal.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.meals}" var="meal">
            <jsp:useBean id="meal" type="com.nutrition.calc.to.MealTo"/>
            <tr data-meal-excess="${meal.excess}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals/update?id=${meal.id}"><fmt:message key="common.update"/></a></td>
                <td><a href="meals/delete?id=${meal.id}"><fmt:message key="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>