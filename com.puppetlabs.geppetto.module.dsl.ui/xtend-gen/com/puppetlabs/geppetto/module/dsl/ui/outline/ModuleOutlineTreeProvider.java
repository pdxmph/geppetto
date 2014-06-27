/**
 * generated by Xtext
 */
package com.puppetlabs.geppetto.module.dsl.ui.outline;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.puppetlabs.geppetto.forge.model.ModuleName;
import com.puppetlabs.geppetto.module.dsl.ModuleUtil;
import com.puppetlabs.geppetto.module.dsl.metadata.JsonArray;
import com.puppetlabs.geppetto.module.dsl.metadata.JsonDependency;
import com.puppetlabs.geppetto.module.dsl.metadata.JsonOS;
import com.puppetlabs.geppetto.module.dsl.metadata.JsonObject;
import com.puppetlabs.geppetto.module.dsl.metadata.JsonRequirement;
import com.puppetlabs.geppetto.module.dsl.metadata.Value;
import java.util.Iterator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class ModuleOutlineTreeProvider extends DefaultOutlineTreeProvider {
  @Inject
  @Extension
  private ModuleUtil _moduleUtil;
  
  public void _createNode(final IOutlineNode parentNode, final JsonArray jsonArray) {
    this.createChildren(parentNode, jsonArray);
  }
  
  public void _createNode(final IOutlineNode parentNode, final JsonObject jsonObject) {
    this.createChildren(parentNode, jsonObject);
  }
  
  public EObjectNode _createNode(final IOutlineNode parentNode, final JsonDependency dependency) {
    EObjectNode _xblockexpression = null;
    {
      final StringBuilder bld = new StringBuilder();
      final String name = this._moduleUtil.getRawName(dependency);
      boolean _notEquals = (!Objects.equal(name, null));
      if (_notEquals) {
        try {
          ModuleName _create = ModuleName.create(name, false);
          _create.toString(bld);
        } catch (final Throwable _t) {
          if (_t instanceof IllegalArgumentException) {
            final IllegalArgumentException e = (IllegalArgumentException)_t;
            bld.append(name);
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
      }
      final String range = this._moduleUtil.getString(dependency, "version_requirement");
      boolean _tripleNotEquals = (range != null);
      if (_tripleNotEquals) {
        bld.append(" ");
        bld.append(range);
      }
      String _string = bld.toString();
      _xblockexpression = new EObjectNode(dependency, parentNode, ((Image) null), _string, true);
    }
    return _xblockexpression;
  }
  
  public EObjectNode _createNode(final IOutlineNode parentNode, final JsonRequirement requirement) {
    EObjectNode _xblockexpression = null;
    {
      final StringBuilder bld = new StringBuilder();
      final String name = this._moduleUtil.getString(requirement, "name");
      boolean _tripleNotEquals = (name != null);
      if (_tripleNotEquals) {
        bld.append(name);
      }
      final String range = this._moduleUtil.getString(requirement, "version_requirement");
      boolean _tripleNotEquals_1 = (range != null);
      if (_tripleNotEquals_1) {
        bld.append(" ");
        bld.append(range);
      }
      String _string = bld.toString();
      _xblockexpression = new EObjectNode(requirement, parentNode, ((Image) null), _string, true);
    }
    return _xblockexpression;
  }
  
  private void appendSQuoted(final StringBuilder bld, final Value value) {
    bld.append("\'");
    String _string = this._moduleUtil.getString(value);
    bld.append(_string);
    bld.append("\'");
  }
  
  public EObjectNode _createNode(final IOutlineNode parentNode, final JsonOS os) {
    EObjectNode _xblockexpression = null;
    {
      final StringBuilder bld = new StringBuilder();
      final String name = this._moduleUtil.getString(os, "operatingsystem");
      boolean _tripleNotEquals = (name != null);
      if (_tripleNotEquals) {
        bld.append(name);
      }
      final Value osSupport = this._moduleUtil.getValue(os, "operatingsystemrelease");
      if ((osSupport instanceof JsonArray)) {
        bld.append(" [");
        EList<Value> _value = ((JsonArray)osSupport).getValue();
        final Iterator<Value> iter = _value.iterator();
        boolean _hasNext = iter.hasNext();
        if (_hasNext) {
          Value _next = iter.next();
          this.appendSQuoted(bld, _next);
          boolean _hasNext_1 = iter.hasNext();
          boolean _while = _hasNext_1;
          while (_while) {
            {
              bld.append(", ");
              Value _next_1 = iter.next();
              this.appendSQuoted(bld, _next_1);
            }
            boolean _hasNext_2 = iter.hasNext();
            _while = _hasNext_2;
          }
        }
        bld.append("]");
      }
      String _string = bld.toString();
      _xblockexpression = new EObjectNode(os, parentNode, ((Image) null), _string, true);
    }
    return _xblockexpression;
  }
}