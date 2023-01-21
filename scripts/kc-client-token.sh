#!/usr/bin/env bash
curl \
  -d "client_id=sample-client" -d "client_secret=$1" \
  -d "scope=sample-scope" \
  -d "grant_type=client_credentials" \
  "localhost:8180/auth/realms/master/protocol/openid-connect/token"  | jq -r '.access_token'
