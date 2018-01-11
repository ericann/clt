package org.clt.service.metadata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.clt.repository.pojo.FieldPermission;
import org.clt.repository.pojo.ObjectPermission;
import org.springframework.stereotype.Service;

@Service
public class CreateObjectMetadataService {
	
	public ObjectPermission convert(List<FieldPermission> fpL) {
		ObjectPermission op = new ObjectPermission();
		
		for(FieldPermission fp : fpL) {
			op.setAdd(fp.getObjectpermission().getAdd());
			op.setDel(fp.getObjectpermission().getDel());
			op.setEdit(fp.getObjectpermission().getEdit());
			op.setId(fp.getObjectpermission().getId());
			op.setObjectName(fp.getObjectpermission().getObjectName());
			op.setRead(fp.getObjectpermission().getRead());
			
			fp.setObjectpermission(null);
		}
		
		op.setFieldpermissions(fpL);
		
		return op;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public Map<String, java.lang.Object> convertAccess(List<Map<String, java.lang.Object>> access) {
		Map<String, java.lang.Object> result = new HashMap<String, java.lang.Object>();
		
		for(Map<String, java.lang.Object> m : access) {
			System.out.println("-- start : " + result);
			String uaId = (String) m.get("uaId"), 
				con_name = (String) m.get("con_name"), 
				m_name = (String) m.get("m_name"), 
				fpId = (String) m.get("fpId"), 
				openId = (String) m.get("openId"), 
				caId = (String) m.get("caId"), 
				opId = (String) m.get("opId"), 
				op_name = (String) m.get("op_name"), 
				conId = (String) m.get("conId"), 
				m_id = (String) m.get("m_id");
			
			if(result.get("conId") == null) {
				result.put("conId", conId);
				result.put("openId", openId);
				result.put("con_name", con_name);
			}
			
			if(result.get(uaId) == null) {
				result.put(uaId, new HashMap<String, java.lang.Object>());
			}
			
			Map<String, java.lang.Object> ua = (Map<String, java.lang.Object>) result.get(uaId);
			if(ua.get(caId) == null) {
				ua.put(caId, new HashMap<String, java.lang.Object>());
			}
			
			Map<String, java.lang.Object> ca = (Map<String, java.lang.Object>) ua.get(caId);
			if(ca.get(m_id) == null) {
				ca.put(m_id, new HashMap<String, java.lang.Object>());
			}
			
			Map<String, java.lang.Object> menu = ((Map<String, java.lang.Object>) ca.get(m_id));
			if(menu.get("objects") == null) {
				Map<String, java.lang.Object> objects = new HashMap<String, java.lang.Object>();
				menu.put("objects", objects);
				menu.put("id", m_id);
				menu.put("name", m_name);
			}
			
			Map<String, java.lang.Object> objects = ((Map<String, java.lang.Object>) menu.get("objects"));
			if(op_name != null) {
				objects.put(op_name, opId);
			}
			
			System.out.println("-- end: " + result);
		}
		
		return result;
	}
	
	public Object CreateObjectMetadata(ObjectPermission op) {
		Object o_ = new Object();
		o_.setTitle(op.getObjectName());
		Set<Action> buttons = createButtonMetadata(op.getAdd(), op.getRead(), op.getEdit(), op.getDel());
		o_.setButtons(buttons);
		Set<Field> fields = CreateFieldMetadata(op.getFieldpermissions());
		o_.setFields(fields);
		
		return o_;
	}
	
	public Set<Field> CreateFieldMetadata(List<FieldPermission> fpL) {
		Set<Field> fields = new HashSet<Field>();
		
		for(FieldPermission fp : fpL) {
			Field f = new Field();
			f.setLabel(fp.getField());
			f.setDefaultValue(null);
			f.setReadonly(!fp.getEdit());
			f.setType("div");
			f.setDisplay(fp.getRead());
			
			fields.add(f);
		}
		return fields;
	}
	
	public Set<Action> createButtonMetadata(Boolean add, Boolean read, Boolean edit, Boolean del) {
		Set<Action> buttons = new HashSet<Action>();
		
		if(add) {
			Action b = new Action();
			b.setLabel("Add");
			b.setFunc(null);
			
			buttons.add(b);
		}
		
//		if(read) {
//			Action b = new Action();
//			b.setLabel("Read");
//			b.setFunc(null);
//			
//			buttons.add(b);
//		}
		
		if(edit) {
			Action b = new Action();
			b.setLabel("Edit");
			b.setFunc(null);
			
			buttons.add(b);
		}
		
		if(del) {
			Action b = new Action();
			b.setLabel("Delete");
			b.setFunc(null);
			
			buttons.add(b);
		}
		
		return buttons;
	}
}
