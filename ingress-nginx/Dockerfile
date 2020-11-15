FROM nginx:stable-alpine
RUN true \
	&& rm -rf /etc/nginx/conf.d \
	&& rm -rf /docker-entrypoint.d \
	&& echo "end of command!" 
COPY etc-nginx-conf.d /etc/nginx/conf.d
