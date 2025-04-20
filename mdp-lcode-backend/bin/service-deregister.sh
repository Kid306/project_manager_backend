#!/bin/bash
######################################################################################
###          注销consul中废弃的服务 
###          sh service-deregister sys-31115
###          cyc 20190221
###
###
#####################################################################################

consul_ip=127.0.0.1
consul_port=8500
# 请输入要注销的服务id ，如 sys-31115
service_id=$1
curl "http://${consul_ip}:${consul_port}/v1/agent/service/deregister/${service_id}" -X PUT
~                                                                                             
