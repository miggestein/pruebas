<?PHP
$hostname_localhost="localhost";
$database_localhost="prueba";
$username_localhost="root";
$password_localhost="";

$json=array();
 if(isset($_GET["id"])){
  $id=$_GET['id'];

  $conexion = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);
  $consulta = "select id,datos from info where id= '{$id}'";
  $resultado = mysqli_query($conexion,$consulta);

  if ($registro=mysqli_fetch_array($resultado)){
  	$json['info'][]=$registro;
  }else{
  	$resulta["id"]=0;
  	$resulta["datos"]='no registra';
  	$json['info'][]=$resulta;
  }

  mysqli_close($conexion);
  echo json_encode($json);
   }else{
   	$resulta["success"]=0;
   	$resulta["message"]='NO FUNCAAAAAAAAAAAAAAA';
   	$json['info'][]=$resulta;
   	echo json_encode($json);
   }
?>