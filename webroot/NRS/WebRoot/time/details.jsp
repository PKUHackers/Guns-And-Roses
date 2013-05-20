<%@ page language="java" pageEncoding="UTF-8" isELIgnored = "false"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <title>Topic · Guns and Roses</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/NRS/time/css/bootstrap.min.css" rel="stylesheet">
    <link href="/NRS/time/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="/NRS/time/css/docs.css" rel="stylesheet">
    <link rel="stylesheet" href="/NRS/time/css/font-awesome.min.css">
    <link rel="stylesheet" href="/NRS/time/css/bootstrap-lightbox.min.css">
    <!-- <link rel="stylesheet" href="css/prettyPhoto.css">
    <link rel="stylesheet" href="css/main.css"> -->



    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->

  </head>

    <!-- Navbar
    ================================================== -->
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Guns and Roses</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="">
                <a href="/NRS/time/index.html">Events</a>
              </li>
              <li class="">
                <a href="/NRS/time/line5.html">TimeLine</a>
              </li>
              <li class="active">
                <a href="/NRS/time/index.html">Detail</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

<!-- Subhead
================================================== -->

  <div class="container content-container">

    <!-- Docs nav
    ================================================== -->
    <div class="row">
      <div class="span6">
      	<c:forEach items="${newsList}" var="news" varStatus="status">
      			<h3>${news.title}</h3>
        		<p><i class="icon-time"></i>${news.pubdate }</p>
				<p>${news.description} ...<a href="${news.url}">阅读更多</a></p>
		</c:forEach>
      </div>
      
      <div class="span5">

		<h4>&nbsp;&nbsp;Related Image</h4>
        <div class="row">
	        		<c:forEach items="${imageList}" var="image" varStatus="status">
		        		<c:if test="${status.index+1 <= 2}">
			        		<div class="span2">
			        		
						        <a data-toggle="lightbox" href="#demoLightbox${status.index+1}" class="thumbnail">
						          <img src="${image.url}" alt="${image.title}">
						        </a>
						        <div id="demoLightbox${status.index+1}" class="lightbox hide fade"  tabindex="-1" role="dialog" aria-hidden="true">
							          <div class='lightbox-content'>
							            <img src="${image.url }">
							            <div class="lightbox-caption"><p>${image.title }</p></div>
							          </div>
						        </div>
					        </div>
		        		</c:if>
					</c:forEach>
        </div>


        <div class="row">
	        <c:forEach items="${imageList}" var="image" varStatus="status">
		        		<c:if test="${status.index+1 > 2}">
			        		<div class="span2">
			        		
						        <a data-toggle="lightbox" href="#demoLightbox${status.index+1}" class="thumbnail">
						          <img src="${image.url}" alt="${image.title}">
						        </a>
						        <div id="demoLightbox${status.index+1}" class="lightbox hide fade"  tabindex="-1" role="dialog" aria-hidden="true">
							          <div class='lightbox-content'>
							            <img src="${image.url }">
							            <div class="lightbox-caption"><p>${image.title }</p></div>
							          </div>
						        </div>
					        </div>
		        		</c:if>
					</c:forEach>
        </div>

      <div class="row">
        <hr />
      </div>

        <h4>&nbsp;&nbsp;Related Video</h4>
        <div class="row">
        	<c:forEach items="${videoList}" var="video" varStatus="status">
		        		<c:if test="${status.index+1 <= 2}">
			        		<div class="span2">
						          <a href="#video${status.index+1}" class="thumbnail" data-toggle="modal"><img src="${video.imgurl}"></a>
						          <div id="video${status.index+1}" class="modal hide fade">
								          <div class="modal-header">
								            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								            <h3>${video.title}</h3>
								          </div>
								          <div class="modal-body">
								            <center><iframe width="420" height="315" src="${video.url}" frameborder="0" allowfullscreen></iframe></center>
								          </div>
							      </div>
					        </div>
		        		</c:if>
					</c:forEach>
      </div>

        <div class="row">
	        <c:forEach items="${videoList}" var="video" varStatus="status">
		        		<c:if test="${status.index+1 > 2}">
			        		<div class="span2">
						          <a href="#video${status.index+1}" class="thumbnail" data-toggle="modal"><img src="${video.imgurl}"></a>
						          <div id="video${status.index+1}" class="modal hide fade">
								          <div class="modal-header">
								            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								            <h3>${video.title}</h3>
								          </div>
								          <div class="modal-body">
								            <center><iframe width="420" height="315" src="${video.url}" frameborder="0" allowfullscreen></iframe></center>
								          </div>
							      </div>
					        </div>
		        		</c:if>
					</c:forEach>
        </div>
    </div>

  </div>



    <!-- Footer
    ================================================== -->
    <footer class="footer">
      <div class="container">
        <p>Designed and built with all the love in the world. Licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
        <ul class="footer-links">
          <li><a href="http://blog.getbootstrap.com">Blog</a></li>
          <li class="muted">&middot;</li>
          <li><a href="https://github.com/twitter/bootstrap/issues?state=open">Issues</a></li>
          <li class="muted">&middot;</li>
          <li><a href="https://github.com/twitter/bootstrap/blob/master/CHANGELOG.md">Changelog</a></li>
        </ul>
      </div>
    </footer>



    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/NRS/time/js/jquery-1.9.1.min.js"></script>
    <script src="/NRS/time/js/bootstrap.min.js"></script>
    <script src="/NRS/time/js/scale.fix.js"></script>
    <script src="/NRS/time/js/bootstrap-lightbox.min.js"></script>
    <!-- <script src="js/plugins.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script> -->



    <!-- Analytics
    ================================================== -->

  </body>
</html>
