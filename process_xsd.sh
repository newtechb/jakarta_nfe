#!/bin/bash

# Caminho para o arquivo schemas.zip
ZIP_FILE="src/main/resources/schemas.zip"
# Diretório temporário para extrair os arquivos XSD
TEMP_DIR="temp_xsd"
# Diretório onde os arquivos Java serão gerados
OUTPUT_DIR="src/main/java"
BINDING_FILE="bindings.xjb"

# Cria os diretórios temporário e de saída, se não existirem
mkdir -p "$TEMP_DIR"
mkdir -p "$OUTPUT_DIR"

# Função para converter nome de arquivo em camel case
convert_to_camel_case() {
    local input="$1"
    local output=""
    local capitalize_next=false

    # Loop através de cada caractere no nome do arquivo
    for (( i=0; i<${#input}; i++ )); do
        local char="${input:$i:1}"
        if [[ "$char" == "-" ]]; then
            capitalize_next=true
        else
            if [[ "$capitalize_next" == true ]]; then
                output+="${char^^}"
                capitalize_next=false
            else
                output+="$char"
            fi
        fi
    done

    echo "$output"
}

# Extrai os arquivos XSD do zip para o diretório temporário
unzip -o "$ZIP_FILE" "*.xsd" -d "$TEMP_DIR"

# Renomeia os arquivos XSD para camel case
for xsd_file in "$TEMP_DIR"/*.xsd; do
    base_name=$(basename "$xsd_file" .xsd)
    if [[ "$base_name" == *-* ]]; then
        new_name=$(convert_to_camel_case "$base_name").xsd
        mv "$xsd_file" "$TEMP_DIR/$new_name"
        echo "Renomeado: $base_name.xsd -> $new_name"
    fi
done

# Função para processar um arquivo XSD
process_xsd() {
    local xsd_file="$1"
    # Extrai o nome do arquivo sem extensão
    local base_name=$(basename "$xsd_file" .xsd)
    # Extrai o nome antes do primeiro '_'
    local package_name=$(echo "$base_name" | cut -d'_' -f1)

    # Cria o pacote completo
    local full_package="org.newtechb.nfe.schemas.$package_name"

    # Executa o xjc para gerar as classes Java
    xjc -b "$BINDING_FILE" -p "$full_package" -d "$OUTPUT_DIR" "$xsd_file"

    # Remove o arquivo XSD após processamento
    rm "$xsd_file"
}

# Processa todos os arquivos XSD no diretório temporário
for xsd_file in "$TEMP_DIR"/*.xsd; do
    process_xsd "$xsd_file"
done

# Remove o diretório temporário
rm -r "$TEMP_DIR"

echo "Processamento concluído."
