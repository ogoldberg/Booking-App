<g:form url='[controller: "band", action: "list"]' id="bandSearchForm"
       name="searchableForm" method="get">
    <g:textField name="q" value="${params.q}" id="bandSearchBox" size="20" />
    <input id="bandSearchButton" type="submit" value="Search" />
</g:form>
