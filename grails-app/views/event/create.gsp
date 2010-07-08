
<%@ page import="net.turfclub.*" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
  <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
  <div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
  </div>
  <div class="body">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${eventInstance}">
      <div class="errors">
        <g:renderErrors bean="${eventInstance}" as="list" />
      </div>
    </g:hasErrors>
    <g:form action="save" method="post" >
      <div class="dialog">
        <table>
            <tbody>
                <tr><th>*Required</th> <td>Click "Create" to start adding bands</td></tr>
          <tr class="prop">
            <td valign="top" class="name">
              <label for="date"><g:message code="event.date.label" default="Date *" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'eventDate', 'errors')}">
          <calendar:resources lang="en" theme="green"/>
          <calendar:datePicker name="eventDate" precision="day" dateFormat="%m/%e/%Y" value="${eventInstance?.eventDate}"  />
          </td>        

      </tr>      
      <tr class="prop">
        <td>
                <label for="eventHour"><g:message code="event.eventDate.label" default="Time *" /></label>
            </td>
            <td>
                <turfclub:eventTimeSelectors date="${eventInstance.eventDate}" />
            </td>
      </tr>

              <tr class="prop">
            <td valign="top" class="name">
              <label for="event"><g:message code="event.booker.label" default="Booker *" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'booker', 'errors')}">
          <g:select name="booker.id"
                    from="${activeBookers}"
                    optionKey="id"
                    optionValue="username"
                    value="${eventInstance?.booker?.id}"  />
                </td>               
        </tr>
  </tbody>
  </table>

  <table>
      <tbody>
      <tr class="prop">
       <td valign="top" class="name">
                <label for="finalized"><g:message code="event.finalized.label" default="Finalized" /></label>
              </td>
              <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'finalized', 'errors')}">
          <g:checkBox name="finalized" value="${eventInstance?.finalized}" />
      </td>
       <td valign="top" class="name">
                <label for="featured"><g:message code="event.featured.label" default="Featured" /></label>
              </td>
              <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'featured', 'errors')}">
          <g:checkBox name="featured" value="${eventInstance?.featured}" />
          </td>
       <td class="name">
              <label for="holdPriority">Hold Priority</label>
          </td>
            <td class="value ${hasErrors(bean: eventInstance, field: 'holdPriority', 'errors')}">
            <g:select id="holdPriority" 
                          name='holdPriority' 
                          from="${1..5}" 
                          value="${eventInstance?.holdPriority}"
                          noSelection="${['0':'--No Hold--']}" />
                  </td>
              </tr>
              </tbody>
          </table>

          <table class="dialog1">
              <tbody>
   <tr class="prop">
            <td valign="top" class="name">
              <label for="cover">Cover $</label>
          </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'cover', 'errors')}">
          <g:textField name="cover" align="right" value="${eventInstance?.cover}" />
      </td>
      </tr>
           <tr class="prop">
       <td valign="top" class="name">
              <label for="imageLink"><g:message code="event.imageLink.label" default="Image Link" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'imageLink', 'errors')}">
          <g:textField name="imageLink" value="${eventInstance?.imageLink}" />
          </td>
      </tr>
       <tr class="prop">
            <td valign="top" class="name">
              <label for="eventTitle"><g:message code="event.eventTitle.label" default="Event Title (optional)" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'eventTitle', 'errors')}">
          <g:textArea name="eventTitle" cols="30" rows="2" value="${eventInstance?.eventTitle}" />
          </td>
          </tr>

          <tr class="prop">
            <td valign="top" class="name">
              <label for="description"><g:message code="event.description.label" default="Description (optional)" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: eventInstance, field: 'description', 'errors')}">
          <g:textArea name="description" cols="30" rows="4" value="${eventInstance?.description}" />
          </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="buttons">
        <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
      </div>
    </g:form>
    <div class="dialog0"></div>
  </div>
</body>
</html>
