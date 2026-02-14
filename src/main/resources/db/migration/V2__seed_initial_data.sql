CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO listing (id,name,normalized_name,category,tags,description,address,latitude,longitude,source_type,confidence_score,is_active)
VALUES
(gen_random_uuid(),'Padaria Central','padaria central','padaria',ARRAY['pao','cafe'],'Padaria no centro','Rua 1',-23.415,-46.036,'curated',0.95,true),
(gen_random_uuid(),'Pet Shop Guará','pet shop guara','pet_shop',ARRAY['pet','banho'],'Pet shop completo','Rua 2',-23.416,-46.037,'curated',0.93,true),
(gen_random_uuid(),'Farmácia Vida','farmacia vida','farmacia',ARRAY['medicamento'],'Farmácia 24h','Rua 3',-23.417,-46.038,'official',0.96,true),
(gen_random_uuid(),'Restaurante Sabor da Serra','restaurante sabor da serra','restaurante',ARRAY['almoco','jantar'],'Comida caseira','Rua 4',-23.418,-46.039,'curated',0.90,true),
(gen_random_uuid(),'Hotel Guararema Inn','hotel guararema inn','hotel',ARRAY['hospedagem'],'Hospedagem familiar','Rua 5',-23.419,-46.031,'partner',0.90,true),
(gen_random_uuid(),'Pousada Rio Paraíba','pousada rio paraiba','pousada',ARRAY['turismo'],'Pousada charmosa','Rua 6',-23.420,-46.032,'partner',0.89,true),
(gen_random_uuid(),'Café Estação','cafe estacao','cafe',ARRAY['cafe','doces'],'Café especial','Rua 7',-23.421,-46.033,'curated',0.88,true),
(gen_random_uuid(),'Pizzaria Forno','pizzaria forno','pizzaria',ARRAY['pizza'],'Pizzaria artesanal','Rua 8',-23.422,-46.034,'curated',0.91,true),
(gen_random_uuid(),'Mercado Bairro','mercado bairro','mercado',ARRAY['supermercado'],'Mercado local','Rua 9',-23.423,-46.035,'community',0.87,true),
(gen_random_uuid(),'Posto Centro','posto centro','posto',ARRAY['combustivel'],'Posto de combustível','Rua 10',-23.424,-46.036,'official',0.94,true),
(gen_random_uuid(),'Academia Movimento','academia movimento','academia',ARRAY['fitness'],'Academia completa','Rua 11',-23.425,-46.037,'curated',0.86,true),
(gen_random_uuid(),'Clínica Saúde','clinica saude','clinica',ARRAY['consulta'],'Clínica geral','Rua 12',-23.426,-46.038,'official',0.93,true),
(gen_random_uuid(),'Lanchonete Praça','lanchonete praca','lanchonete',ARRAY['lanche'],'Lanches rápidos','Rua 13',-23.427,-46.039,'curated',0.85,true),
(gen_random_uuid(),'Loja Moda Centro','loja moda centro','loja',ARRAY['roupa'],'Moda feminina','Rua 14',-23.428,-46.040,'community',0.83,true),
(gen_random_uuid(),'Oficina Rápida','oficina rapida','oficina',ARRAY['carro'],'Mecânica geral','Rua 15',-23.429,-46.041,'curated',0.84,true),
(gen_random_uuid(),'Churrascaria Vale','churrascaria vale','restaurante',ARRAY['churrasco'],'Rodízio','Rua 16',-23.430,-46.042,'partner',0.92,true),
(gen_random_uuid(),'Sorveteria Doce Frio','sorveteria doce frio','sorveteria',ARRAY['sorvete'],'Sorvetes artesanais','Rua 17',-23.431,-46.043,'community',0.82,true),
(gen_random_uuid(),'Borracharia 24h','borracharia 24h','borracharia',ARRAY['pneu'],'Serviço rápido','Rua 18',-23.432,-46.044,'official',0.88,true),
(gen_random_uuid(),'Floricultura Primavera','floricultura primavera','floricultura',ARRAY['flores'],'Arranjos e presentes','Rua 19',-23.433,-46.045,'curated',0.86,true),
(gen_random_uuid(),'Biblioteca Municipal','biblioteca municipal','utilidade',ARRAY['cultura'],'Biblioteca pública','Rua 20',-23.434,-46.046,'official',0.98,true);

INSERT INTO event (id,title,normalized_title,start_at,end_at,venue_name,address,latitude,longitude,source_type,confidence_score,is_active)
VALUES
(gen_random_uuid(),'Feira de Artesanato','feira de artesanato',now()+interval '1 day',now()+interval '1 day 4 hour','Praça Central','Praça Central',-23.42,-46.03,'official',0.95,true),
(gen_random_uuid(),'Festival Gastronômico','festival gastronomico',now()+interval '2 day',now()+interval '2 day 6 hour','Parque Municipal','Parque',-23.41,-46.04,'official',0.95,true),
(gen_random_uuid(),'Show na Estação','show na estacao',now()+interval '3 day',now()+interval '3 day 3 hour','Estação','Estação',-23.40,-46.02,'partner',0.90,true),
(gen_random_uuid(),'Corrida de Rua','corrida de rua',now()+interval '4 day',now()+interval '4 day 2 hour','Centro','Centro',-23.43,-46.05,'official',0.93,true),
(gen_random_uuid(),'Cinema ao Ar Livre','cinema ao ar livre',now()+interval '5 day',now()+interval '5 day 2 hour','Praça','Praça',-23.44,-46.06,'community',0.87,true),
(gen_random_uuid(),'Sarau Cultural','sarau cultural',now()+interval '6 day',now()+interval '6 day 2 hour','Biblioteca','Biblioteca',-23.39,-46.01,'official',0.91,true),
(gen_random_uuid(),'Festival de Inverno','festival de inverno',now()+interval '7 day',now()+interval '7 day 5 hour','Parque','Parque',-23.38,-46.00,'official',0.94,true),
(gen_random_uuid(),'Aula de Yoga Pública','aula de yoga publica',now()+interval '8 day',now()+interval '8 day 1 hour','Lago','Lago',-23.37,-46.07,'community',0.82,true),
(gen_random_uuid(),'Encontro de Carros Antigos','encontro de carros antigos',now()+interval '9 day',now()+interval '9 day 4 hour','Pavilhão','Pavilhão',-23.36,-46.08,'partner',0.90,true),
(gen_random_uuid(),'Festa da Cidade','festa da cidade',now()+interval '10 day',now()+interval '10 day 8 hour','Centro','Centro',-23.35,-46.09,'official',0.99,true);

INSERT INTO term_synonym (id,term,synonym,weight) VALUES
(gen_random_uuid(),'pet shoop','pet shop',1),(gen_random_uuid(),'pety shop','pet shop',1),(gen_random_uuid(),'farmacia','farmacia',1),(gen_random_uuid(),'drogaria','farmacia',0.9),(gen_random_uuid(),'remedio','farmacia',0.8),
(gen_random_uuid(),'lancheria','lanchonete',0.9),(gen_random_uuid(),'pizza','pizzaria',0.9),(gen_random_uuid(),'pousadinha','pousada',0.8),(gen_random_uuid(),'hotelaria','hotel',0.8),(gen_random_uuid(),'gasosa','posto',0.7),
(gen_random_uuid(),'posto gasolina','posto',1),(gen_random_uuid(),'mecanica','oficina',0.9),(gen_random_uuid(),'auto mecanica','oficina',1),(gen_random_uuid(),'sorvete','sorveteria',0.8),(gen_random_uuid(),'flores','floricultura',0.8),
(gen_random_uuid(),'livraria publica','biblioteca',0.8),(gen_random_uuid(),'academia','academia',1),(gen_random_uuid(),'ginasio','academia',0.8),(gen_random_uuid(),'comida','restaurante',0.7),(gen_random_uuid(),'almoco','restaurante',0.8),
(gen_random_uuid(),'janta','restaurante',0.8),(gen_random_uuid(),'cafezinho','cafe',0.8),(gen_random_uuid(),'bar','lanchonete',0.7),(gen_random_uuid(),'supermercado','mercado',1),(gen_random_uuid(),'mercadinho','mercado',0.9),
(gen_random_uuid(),'evento','evento',1),(gen_random_uuid(),'festa','evento',0.9),(gen_random_uuid(),'show','evento',0.8),(gen_random_uuid(),'feira','evento',0.8),(gen_random_uuid(),'programacao','evento',0.8),
(gen_random_uuid(),'aberto agora','aberto',1),(gen_random_uuid(),'abre amanha','amanha',1),(gen_random_uuid(),'rota','como chegar',1),(gen_random_uuid(),'mapa','como chegar',0.9),(gen_random_uuid(),'perto','proximo',0.8),
(gen_random_uuid(),'próximo','proximo',1),(gen_random_uuid(),'clinica','clinica',1),(gen_random_uuid(),'hospital','clinica',0.7),(gen_random_uuid(),'churrasco','churrascaria',0.9),(gen_random_uuid(),'rodizio','churrascaria',0.8),
(gen_random_uuid(),'pao','padaria',0.8),(gen_random_uuid(),'panificadora','padaria',0.9),(gen_random_uuid(),'turismo','pousada',0.6),(gen_random_uuid(),'hospedagem','hotel',0.9),(gen_random_uuid(),'telefone','contato',0.5),
(gen_random_uuid(),'instagram','instagram',1),(gen_random_uuid(),'whats','whatsapp',1),(gen_random_uuid(),'zap','whatsapp',0.9),(gen_random_uuid(),'endereco','local',0.8),(gen_random_uuid(),'localizacao','local',0.8),
(gen_random_uuid(),'loja','comercio',0.7),(gen_random_uuid(),'utilidade','servico',0.6),(gen_random_uuid(),'servico','servico',1),(gen_random_uuid(),'pet','pet shop',0.8),(gen_random_uuid(),'petshop','pet shop',1);

INSERT INTO intent_alias_map (id,alias,canonical_intent) VALUES
(gen_random_uuid(),'onde','FIND_PLACE'),(gen_random_uuid(),'evento','FIND_EVENT'),(gen_random_uuid(),'aberto','CHECK_OPEN_HOURS'),
(gen_random_uuid(),'rota','GET_ROUTE'),(gen_random_uuid(),'como','GET_ROUTE'),(gen_random_uuid(),'sugestao','FALLBACK_RELATED');
