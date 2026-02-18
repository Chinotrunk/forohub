-- Insertar respuestas de ejemplo (topico_id=1, autor_id=1)
INSERT INTO respuestas (mensaje, fecha_creacion, autor_id, topico_id, solucion) VALUES
                                                                                    ('Debes usar @Transactional en la capa de servicio para que Spring gestione las transacciones.', NOW(), 1, 1, TRUE),
                                                                                    ('Revisa que tu servicio MySQL esté corriendo en el puerto 3306.', NOW(), 1, 2, FALSE),
                                                                                    ('Es recomendable usar localStorage o sessionStorage con precaución, o mejor httpOnly cookies.', NOW(), 1, 3, TRUE);