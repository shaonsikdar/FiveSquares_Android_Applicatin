<?php header('content-type: application/json; charset=utf-8');
/**
 * File to handle all API requests
 * Accepts POST method
 *
 * Each request will be identified by TAG
 * Response will be JSON data
 */

$sql;

if(isset($_REQUEST['tag']) && $_REQUEST['tag']!='') {
    
	mysql_connect("localhost","root","");
	mysql_select_db("friendsboxes");
 
	$tag = $_REQUEST['tag'];  
        
        $response = array("tag" => $tag , "success" => 0, "error" => 0);

      echo "hello";  
	
 if($tag == "register") {
      
	  
	  
		try{
					
					//http://localhost/friendsboxes/fb_json.php?tag=register&name=Shaon&surname=Sikdar&email=shaon_sikdar@yahoo.com&gender=male&age=23&country=xxxxx&region=xxx&city=xx&mobile=0167092342&hobby=thinking
					
                                        // if success JSON return : {"tag":"register","success":1,"error":0}

                                        if( isset($_REQUEST["name"]) && $_REQUEST["name"] != '' &&
                                                isset($_REQUEST["surname"]) && $_REQUEST["surname"] != '' &&
                                                isset($_REQUEST["email"]) && $_REQUEST["email"] != '' &&
                                                isset($_REQUEST["gender"]) && $_REQUEST["gender"] != '' &&
                                                isset($_REQUEST["age"]) && $_REQUEST["age"] != '' &&
                                                isset($_REQUEST["country"]) && $_REQUEST["country"] != '' &&
                                                isset($_REQUEST["region"]) && $_REQUEST["region"] != '' &&
                                                isset($_REQUEST["city"]) && $_REQUEST["city"] != '' &&
                                                isset($_REQUEST["mobile"]) && $_REQUEST["mobile"] != '' &&
                                                isset($_REQUEST["hobby"]) && $_REQUEST["hobby"] != ''
                                        ){

                                                $Name = $_REQUEST["name"];
                                                $Surname = $_REQUEST["surname"];
                                                $Email = $_REQUEST["email"];
                                                $Gender = $_REQUEST["gender"];
                                                $Age = $_REQUEST["age"];
                                                $Country = $_REQUEST["country"];
                                                $Region = $_REQUEST["region"];
                                                $City = $_REQUEST["city"];
                                                $Mobile = $_REQUEST["mobile"];
                                                $HobbyName = $_REQUEST["hobby"];
               

                                                $query = mysql_query("insert into fb_users (Name,Surname,Email,Gender,Age,Country,Region,City,Mobile,HobbyName)values('$Name','$Surname','$Email','$Gender','$Age','$Country','$Region','$City','$Mobile','$HobbyName')") ;

                                                if($query>0){

                                                        $response["success"] = 1;


                                                }else{

                                                        $response["error"] = 1;
                                                        $response["error_msg"] = "Registratin not complete.";

                                                }

                                                echo json_encode($response);  

                                    }else{

                                                        $response["error"] = 1;
                                                        $response["error_msg"] = "Registratin not complete.";

                                                }

                                                echo json_encode($response);
                                                                           
					
			}catch(Exception $e){
			
				echo 'Exception: '.$e->getMessage();
				
			}
      
  }else if($tag == "userinfo" ) {
      
	  try{
					
					//Example URL:http://localhost/friendsboxes/fb_json.php?tag=userinfo&surname=Sikdar

                                       //If success return :
                                       //{"tag":"userinfo","success":1,"error":0,"user":[{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"}]}
					

                                       if( isset($_REQUEST["surname"]) && $_REQUEST["surname"] != '' )

					{
                                                $Surname = $_REQUEST["surname"];

                                                $sql = mysql_query("Select Name,Surname,Email,Gender,Age,Country,Region,City,Mobile,HobbyName from fb_users where Surname='$Surname'") ;
                                               
                                               //$rows = array();
					
                                                while($r = mysql_fetch_assoc($sql)) {
						
        						$response["user"][] = $r;
                				}
                                                
                                                
                                                $values = mysql_fetch_array($sql);
                                                echo $values;

                                                //var_dump($values);
                                                //echo $values;

                        			if($sql>0){

                                			$response["success"] = 1;
						
                                        	}else{
					
                                                    $response["error"] = 1;
                                                    $response["error_msg"] = "Username does not found.";
						
                                                }

                                        }else{
                                                
                                                    $response["error"] = 1;
                                                    $response["error_msg"] = "Check your query.";
            
                                        }
					
					echo json_encode($response);                                                                             
					
			}catch(Exception $e){

				echo 'Exception: '.$e->getMessage();

			}
	  
  } else if($tag == "user") {
      
	  try{
					
					//Example URL: localhost/friendsboxes/fb_json.php?tag=userhobby&hobby=chating
                                           // 2 : http://localhost/friendsboxes/fb_json.php?tag=user&by=hobby&hobby=think
                                        
                                         // JSON Return
                                         // If success
                                         //{"user":[{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"}],"success":1}
                                         // Error
                                         //
					
                                        if(isset($_REQUEST["by"]) && $_REQUEST["by"] !=''){
        
                                            $searchBy = $_REQUEST['by'];
                                            $response = array("by"=>$searchBy);
                                            
                                        }

					if($searchBy=="hobby"){
						
						//Example URL: http://localhost/friendsboxes/fb_json.php?tag=user&by=hobby&hobby=chating
						
                                                $HobbyName = $_REQUEST["hobby"];
						$sql = mysql_query("Select Name,Surname,Email,Gender,Age,Country,Region,City,Mobile,HobbyName from fb_users where
                                                HobbyName LIKE '%$HobbyName%'") ;
						
						checking($sql);
					
					}else if($searchBy=="email"){
						
                                                
						//http://localhost/friendsboxes/fb_json.php?tag=user&by=email&email=shaon_sikdar@yahoo.c
                                                //If success
                                                //{"user":[{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"}],"success":1}
                                                //Error
                                                //{"success":1}
                                                
                                             $Email = $_REQUEST["email"];

                                             $sql = mysql_query("Select Name,Surname,Email,Gender,Age,Country,Region,City,Mobile,HobbyName from fb_users where
					Email LIKE '$Email'") ;
					
							checking($sql);
					
					}else if($searchBy=="mobile"){
						
						//Example URL: http://localhost/friendsboxes/fb_json.php?tag=user&by=mobile&mobile=167092342
                                                //if success 
                                                //JSON :{"user":[{"Name":"Shaon","Surname":"Sikdar","Email":"shaon_sikdar@yahoo.c","Gender":"male","Age":"23","Country":"xxxxx","Region":"xxx","City":"xx","Mobile":"167092342","HobbyName":"thinking"}],"success":1}
                                                //Error:
                                                //{"success":1}
                                                
                                                $Mobile = $_REQUEST["mobile"];
						
                                                $sql = mysql_query("Select Name,Surname,Email,Gender,Age,Country,Region,City,Mobile,HobbyName from fb_users where
					Mobile='$Mobile'") ;

							checking($sql);
					
					}else{
						
						$response["error"] = 1;
						$response["error_msg_cat"] = "Category Search Surname does not found.";
					
					}//end of else if condition
				
			}catch(Exception $e){
				echo 'Exception: '.$e->getMessage();
		}//end of exception handling 
	}else {
        
		echo "Invalid Request";
  }
  
} else {
    echo "Access Denied";
}

//Wrtie a new line before checking function

function checking($sql){
			
                        			if($sql>0){
							

								while($r = mysql_fetch_assoc($sql)) {
								
										$response["user"][] = $r;
								}
							
                                                         
                                                                $response["success"] = 1;
								
							}else{
							
								$response["error"] = 1;
								$response["error_msg"] = "By Hobby Username does not found.";
								
							}
							
							echo json_encode($response);  
							
					}//end of checking()

?>