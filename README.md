# GreenGo-ProyectoIntermodular

#code:
 if (method.equalsIgnoreCase("POST")) {
                    addCorsHeaders(exchange);
                    try {
                        byte[] bytes = exchange.getRequestBody().readAllBytes();
                        String body = new String(bytes, StandardCharsets.UTF_8);
                        System.out.println("Cuerpo recibido: " + body);

                        JsonObject raiz = JsonParser.parseString(body).getAsJsonObject();
                        System.out.println(raiz.toString());

                        if (!raiz.has("nombre") || !raiz.has("email") || !raiz.has("contraseña")) {
                            sendResponse(exchange, 400, "{\"error\":\"Faltan campos requeridos\"}");
                            return;
                        }

                        String nombre = raiz.get("nombre").getAsString();
                        String email = raiz.get("email").getAsString();
                        String contraseña = raiz.get("contraseña").getAsString();
                        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!%@*?&])[A-Za-z\\d!%@*?&]{5,10}$";
                        if ( contraseña.matches(regex)){
                            System.out.println("Validación correcta");
                            String bcryptHashString = BCrypt.withDefaults().hashToString(12, contraseña.toCharArray());
                            System.out.println("Datos: " + nombre + ", " + email + ", " + bcryptHashString);
                            usuario.insertarUsuario(nombre, email, bcryptHashString);
                        }else {
                            System.out.println("La contraseña debe incluir al menos una mayuscula, un caracter especial, un digito y estar entre 5 y 10 chars");
                        }






                        sendResponse(exchange, 200, gson.toJson(raiz));

                    } catch (Exception e) {
                        e.printStackTrace();
                        sendResponse(exchange, 500, "{\"error\":\"Error al procesar la solicitud\"}");
                    }
                    return;
                }

                sendResponse(exchange, 405, "{\"error\":\"Método no permitido\"}");
            }
