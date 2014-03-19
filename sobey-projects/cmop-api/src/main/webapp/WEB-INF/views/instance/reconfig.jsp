<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/taglib.jsp"%>

<html>
<head>
<title>Instance Reconfig Demo</title>
</head>

<body>

	<form class="form-horizontal" role="form" method="post" action=".">

		<div class="form-group">
			<label for="vmName" class="col-sm-2 control-label">VMName</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="vmName" name="vmName"
					placeholder="虚拟机名称" value="Sobey">
			</div>
		</div>

		<div class="form-group">
			<label for="cpuNumber" class="col-sm-2 control-label">CPU
				Number</label>
			<div class="col-sm-10">
				<select id="cpuNumber" name="cpuNumber">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="4">4</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="memoryMB" class="col-sm-2 control-label">Memory
				Size</label>
			<div class="col-sm-10">
				<select id="memoryMB" name="memoryMB">
					<option value="1024">1G</option>
					<option value="2048">2G</option>
					<option value="4096">4G</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary">Reconfig</button>
			</div>
		</div>
	</form>

</body>
</html>
