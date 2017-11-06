Para ejecutar:
1. Asegurar de que estan inciado tambien el servicio de autorizacion y autenticacion (my-auth-service)
2. Generar el token:
curl   -X POST   -H"authorization: Basic aHRtbDU6cGFzc3dvcmQ="   -F"password=spring"   -F"client_secret=password"   -F"client_id=html5"   -F"username=jlong"   -F"grant_type=password"   -F"scope=openid"   http://localhost:8080/uaa/oauth/token |json_pp
3. Tomar el token y enviarlo a este servicodr de recursos:
curl -X GET -H"authorization: bearer b6021a1a-5d1a-4bfc-b48c-3a454aee9846" http://localhost:8082/greet/nelson