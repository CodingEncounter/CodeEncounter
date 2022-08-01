<%--<%@ tag language="java" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>--%>

<%--&lt;%&ndash;---------------------- ATTRIBUTES ----------------------&ndash;%&gt;--%>
<%--&lt;%&ndash; Spring Page Object &ndash;%&gt;--%>
<%--<%@ attribute name="page" required="true" type="org.springframework.data.domain.Page" %>--%>
<%--&lt;%&ndash; Page Base URL &ndash;%&gt;--%>
<%--<%@ attribute name="url" required="true" %>--%>

<%--&lt;%&ndash; check if baseURL contains ? - ternary Operator &ndash;%&gt;--%>
<%--<c:set var="paramListSeparator" value="${fn:contains(url, '?') ? '&':'?' }"/>--%>

<%--&lt;%&ndash; Number of page numbers to display at once. &ndash;%&gt;--%>
<%--<%@ attribute name="size" required="false" %>--%>


<%--&lt;%&ndash;------------------ ATTRIBUTE CALCULATIONS -----------------&ndash;%&gt;--%>
<%--<c:set var="size" value="${empty size ? 10 : size}" />--%>

<%--&lt;%&ndash; If empty, set b(block) parameter to 0 - Else set value to block parameter. &ndash;%&gt;--%>
<%--<c:set var="block" value="${empty param.b ? 0 : param.b}" />--%>

<%--<c:set var="startPage" value="${block * size + 1}"/>--%>
<%--<c:set var="endPage" value="${(block + 1) * size}"/>--%>
<%--<c:set var="endPage" value="${endPage > page.totalPages ? page.totalPages : endPage}"/>--%>


<%--&lt;%&ndash;------------------ OUTPUT -----------------&ndash;%&gt;--%>
<%--<c:if test="${page.totalPages != 1}">--%>

<%--    <div class="pagination">--%>

<%--        <c:if test="${block != 0}">--%>
<%--            <a href="${url}${paramListSeparator}p=${(block-1)*size+1}&b=${block-1}">&lt;&lt;</a>--%>
<%--        </c:if>--%>

<%--        <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">--%>

<%--            <c:choose>--%>
<%--                <c:when test="${page.number != pageNumber - 1}">--%>
<%--                    <a href="${url}${paramListSeparator}p=${pageNumber}&b=${block}"><c:out value="${pageNumber}" /></a>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    <strong> <c:out value="${pageNumber}" /> </strong>--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>


<%--            <c:if test="${pageNumber != endPage}">--%>
<%--                |--%>
<%--            </c:if>--%>
<%--        </c:forEach>--%>

<%--        <c:if test="${endPage != page.totalPages}">--%>
<%--            <a href="${url}${paramListSeparator}p=${(block+1)*size+1}&b=${block+1}">&gt;&gt;</a>--%>
<%--        </c:if>--%>
<%--    </div>--%>

<%--</c:if>--%>