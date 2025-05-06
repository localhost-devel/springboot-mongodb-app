{{/*
Generate full name for resources
*/}}
{{- define "springboot-mongo.fullname" -}}
{{- printf "%s-%s" .Release.Name .Chart.Name | trunc 63 | trimSuffix "-" -}}
{{- end }}

{{/*
Get namespace
*/}}
{{- define "springboot-mongo.namespace" -}}
{{ .Values.namespace | default .Release.Namespace }}
{{- end }}

{{/*
Check if environment is production
*/}}
{{- define "springboot-mongo.isProd" -}}
{{ eq .Values.environment "prod" }}
{{- end }}
