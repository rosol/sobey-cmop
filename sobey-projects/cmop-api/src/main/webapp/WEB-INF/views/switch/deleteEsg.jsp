<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>

<html>
<head>
<title>Esg Delete Demo</title>
</head>

<body>

	<form class="form-horizontal" role="form" method="post" action=".">


		<div class="page-header">
			<h3>安全组ESG删除页面</h3>
		</div>

		<div class="form-group">
			<label for="aclNumber" class="col-sm-2 control-label">AclNumber</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="aclNumber"
					name="aclNumber" placeholder="AclNumber" value="3000">
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-4">
				<button type="submit" class="btn btn-primary">Delete</button>
			</div>
		</div>
	</form>

</body>
</html>
